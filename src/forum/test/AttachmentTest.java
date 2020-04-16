package forum.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import forum.*;

public class AttachmentTest {
	
	private Forum forum = new Forum();
/*
	@Test
	public void testFindById() {
		Attachment attachment = forum.attachmentService.findById(1);
		Assert.assertEquals(attachment.getFileLink(), "krug.jpg");
		attachment = forum.attachmentService.findById(0);
		Assert.assertNull(attachment);
	}
	
	@Test(expectedExceptions = java.lang.NullPointerException.class)
	public void testAddAttachmentWithNullFields() {
		Attachment attachment = new Attachment(null, null);
		forum.attachmentService.addAttachment(attachment);
	}
	
	@Test
	public void testAddAttachment() {
		Post post = forum.postService.findById(7);
		Attachment attachment = new Attachment(post, "games.jpg");
		forum.attachmentService.addAttachment(attachment);
		Attachment sameAttachment = forum.attachmentService.findById(attachment.getAttachmentId());
		Assert.assertEquals(sameAttachment.toString(), attachment.toString());
	}
	*/
}
