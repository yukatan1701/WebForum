package forum.test;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.Test;

import forum.*;

public class PostTest {
	
	private Forum forum = new Forum();

	@Test
	public void testFindById() {
		Post post = forum.postService.findById(1);
		Assert.assertEquals(post.getText(), "Открыта тема по року, металлу и смежным направлениям.");
		post = forum.postService.findById(0);
		Assert.assertNull(post);
	}
	
	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void testAddPostWithNullFields() {
		Post post = new Post(null, null, null, null);
		forum.postService.addPost(post);
	}
	
	@Test
	public void testAddPost() {
		Topic topic = forum.topicService.findById(2);
		User user = forum.userService.findById(2);
		Post post = new Post(topic, user, "Михаил Круг топ.", Timestamp.valueOf("2017-02-01 00:00:02.0"));
		forum.postService.addPost(post);
		Post samePost = forum.postService.findById(post.getPostId());
		Assert.assertEquals(samePost.toString(), post.toString());
	}
}
