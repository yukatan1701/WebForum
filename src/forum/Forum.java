package forum;

import java.text.SimpleDateFormat;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Forum {
	public AttachmentService attachmentService;
	public PostService postService;
	public SectionService sectionService;
	public TopicService topicService;
	public UserService userService;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Configuration configuration;
	SessionFactory sessionFactory;
	
	public Forum() {
		try {
			//configuration = new Configuration().configure();
			//sessionFactory = configuration.buildSessionFactory();
			sessionFactory = DBSession.getSessionFactory();
			attachmentService = new AttachmentService(sessionFactory);
			postService = new PostService(sessionFactory);
			sectionService = new SectionService(sessionFactory);
			topicService = new TopicService(sessionFactory);
			userService = new UserService(sessionFactory);
		} catch (Exception e) {
			throw new IllegalStateException("Could not build session factory");
		}
	}
}
