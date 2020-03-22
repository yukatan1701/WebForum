package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Topic.
 * @see forum.Topic
 * @author Hibernate Tools
 */
public class TopicService {
	
	private static TopicDao topicDao;
	
	public TopicService() {
		topicDao = new TopicDao();
	}

	public void persist(Topic transientInstance) {
		topicDao.openCurrentSessionwithTransaction();
		topicDao.persist(transientInstance);
		topicDao.closeCurrentSessionwithTransaction();
	}
	
	public void update(Topic entity) {
        topicDao.openCurrentSessionwithTransaction();
        topicDao.update(entity);
        topicDao.closeCurrentSessionwithTransaction();
    }
 
    public Topic findById(Integer id) {
    	topicDao.openCurrentSession();
        Topic topic = topicDao.findById(id);
        topicDao.closeCurrentSession();
        return topic;
    }
 
    public void delete(Integer id) {
    	topicDao.openCurrentSessionwithTransaction();
        Topic topic = topicDao.findById(id);
        topicDao.delete(topic);
        topicDao.closeCurrentSessionwithTransaction();
    }

    public List<Topic> findAll() {
        topicDao.openCurrentSession();
        List<Topic> topics = topicDao.findAll();
        topicDao.closeCurrentSession();
        return topics;
    }
 
    public void deleteAll() {
    	topicDao.openCurrentSessionwithTransaction();
    	topicDao.deleteAll();
    	topicDao.closeCurrentSessionwithTransaction();
    }
    
    public void createTopic(Topic entity) {
    	persist(entity);
    }
 
    public TopicDao topicDao() {
        return topicDao;
    }
}
