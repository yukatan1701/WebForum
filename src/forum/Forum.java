package forum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Forum {
	public final AttachmentService attachmentService = new AttachmentService();
	public final PostService postService = new PostService();
	public final SectionService sectionService = new SectionService();
	public final TopicService topicService = new TopicService();
	public final UserService userService = new UserService();
	public final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
}
