package forum;
import forum.enums.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;

public class App {

	public static void main(String[] args) {
		Forum forum = new Forum();
		/*Topic topic = forum.topicService.findById(2);
		User user = forum.userService.findById(5);
		System.out.println(topic.getTitle() + user.getLogin());
		Post post = new Post(topic, user, "Hello!", new Date());
		Attachment at = new Attachment(post, "newlink.png");
		post.addAttachment(at);
		System.out.print(post.toString());
		forum.postService.createPost(post);
		forum.postService.deletePost(post);
		List<Attachment> ats = forum.attachmentService.findAll();
		ats.forEach(a -> System.out.println(a.getFileLink()));*/
		HashMap<Section, HashSet<User>> all = forum.sectionService.getUsersBySections();
		for (Section sec : all.keySet()) {
			System.out.print(sec.getTitle() + ": ");
			all.get(sec).forEach(user -> System.out.print(user.getLogin() + " "));
			System.out.println();
		}
		//byte[] password = mda.digest("helsinki".getBytes());
		//System.out.println(MessageDigest.isEqual(password, user.getPassword()));
		//forum.postService.createPost(post);
		//System.out.printf("%d %d\n", post.getPostId(), at.getPost().getPostId());
	}

}
