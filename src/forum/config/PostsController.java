package forum.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.*;

@Controller
public class PostsController {
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/posts")
	public ModelAndView posts(@RequestParam("topic_id") int id) {
		ModelAndView model = new ModelAndView("posts");
		Topic topic = topicService.findById(id);
		List<Post> posts = new ArrayList<Post>();
		topic.getPosts().forEach(post -> posts.add((Post) post));
		Collections.sort(posts, Post.postComparator);
		model.addObject("topic", topic);
		model.addObject("postList", posts);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
	
	@RequestMapping(value= "/posts/add_post", method = RequestMethod.POST)
	public String addPost(@RequestParam("topic_id") int id, @RequestParam("username") String login, @RequestParam("text") String text) {
		User user = userService.findByLogin(login);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		Post p = new Post(topicService.findById(id), user, text, ts);
		System.out.println(text);
		this.postService.addPost(p);
		return "redirect:/posts?topic_id=" + String.valueOf(id);
	}
	
	@RequestMapping(value="/posts/delete_post")
	public String deleteTopic(@RequestParam("topic_id") int topic_id, @RequestParam("post_id") int post_id) {
		postService.deleteById(post_id);
		return "redirect:/posts?topic_id=" + topic_id;       
	}
	
}
