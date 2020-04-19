package forum.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import forum.*;
import forum.enums.Permissions;

@Controller
public class PostsController {
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ServletContext servletContext;
	
	String rootPath = null;
	String shortPath = null;
	String filesDir = "files";
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/posts")
	public ModelAndView posts(@RequestParam("topic_id") int id, @RequestParam(value="error_type", required=false) String error) {
		ModelAndView model = new ModelAndView("posts");
		if (error != null) {
			if (error.equals("bad_permissions")) {
				model.addObject("delete_post_error", "Недостаточно прав");
			}
		}
		Topic topic = topicService.findById(id);
		List<Post> posts = new ArrayList<Post>();
		topic.getPosts().forEach(post -> posts.add((Post) post));
		Collections.sort(posts, Post.postComparator);
		model.addObject("topic", topic);
		model.addObject("postList", posts);
		rootPath = System.getProperty("com.sun.aas.instanceRoot") + File.separator + "docroot" + File.separator + filesDir;
		shortPath = servletContext.getContextPath() + File.separator + filesDir;
		System.out.println(shortPath);
		System.out.println(rootPath);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
	
	@RequestMapping(value= "/posts/add_post", method = RequestMethod.POST)
	public String addPost(@RequestParam("file") MultipartFile[] files, @RequestParam("topic_id") int id, @RequestParam("username") String login, @RequestParam("text") String text) {
		User user = userService.findByLogin(login);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		String curTime = ts.toString().replace(':', '_').replace('-', '_').replace('.', '_').replace(' ', '_');
		Post p = new Post(topicService.findById(id), user, text, ts);
		this.postService.addPost(p);
		List<Attachment> atts = new ArrayList<Attachment>();
		
		for (MultipartFile file: files) {
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(rootPath + File.separator + curTime);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				Path path = Paths.get(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.write(path, bytes);
				System.out.println("File was saved to: " + path.toString());
				Attachment at = new Attachment(p, shortPath + File.separator + curTime + File.separator + file.getOriginalFilename());
				attachmentService.addAttachment(at);
				atts.add(at);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (Attachment at : atts) {
			p.addAttachment(at);
		}
		return "redirect:/posts?topic_id=" + String.valueOf(id);
	}
	
	@RequestMapping(value="/posts/delete_post")
	public String deleteTopic(@RequestParam("topic_id") int topic_id, @RequestParam("post_id") int post_id,
			@RequestParam("login") String login) {
		User user = userService.findByLogin(login);
		if (user.getPermissions() == Permissions.USER) {
			return "redirect:/posts?topic_id=" + topic_id + "&error_type=bad_permissions";
		}
		postService.deleteById(post_id);
		return "redirect:/posts?topic_id=" + topic_id;       
	}
	
}
