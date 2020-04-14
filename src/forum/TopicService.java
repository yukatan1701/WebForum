package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Topic.
 * @see forum.Topic
 * @author Hibernate Tools
 */

@Component
public class TopicService {
	
	private static TopicDao topicDao;
	
	public TopicService(SessionFactory sessionFactory) {
		topicDao = new TopicDao(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void persist(Topic topic) {
		topicDao.openCurrentSessionwithTransaction();
		topicDao.persist(topic);
		topic.getSection().getTopics().add(topic);
		topicDao.closeCurrentSessionwithTransaction();
	}
	
	@Transactional
	public void update(Topic entity) {
        topicDao.openCurrentSessionwithTransaction();
        topicDao.update(entity);
        topicDao.closeCurrentSessionwithTransaction();
    }
 
	@Transactional
    public Topic findById(Integer id) {
    	topicDao.openCurrentSession();
        Topic topic = topicDao.findById(id);
        topicDao.closeCurrentSession();
        return topic;
    }
    
	@Transactional
    @SuppressWarnings("unchecked")
	public Topic findByTitle(Section section, String title) {
    	Set<Topic> topics = section.getTopics();
    	for (Topic topic : topics) {
    		if (topic.getTitle().equals(title)) {
    			return topic;
    		}
    	}
    	return null;
    }
 
	@Transactional
    public void deleteById(Integer id) {
    	topicDao.openCurrentSessionwithTransaction();
        Topic topic = topicDao.findById(id);
        topic.getSection().getTopics().remove(topic);
        topicDao.delete(topic);
        topicDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void delete(Topic topic) {
    	topicDao.openCurrentSessionwithTransaction();
        topicDao.delete(topic);
        topicDao.closeCurrentSessionwithTransaction();
    }

	@Transactional
    public List<Topic> findAll() {
        topicDao.openCurrentSession();
        List<Topic> topics = topicDao.findAll();
        topicDao.closeCurrentSession();
        return topics;
    }
 
	@Transactional
    public void deleteAll() {
    	topicDao.openCurrentSessionwithTransaction();
    	topicDao.deleteAll();
    	topicDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public boolean ifTopicInSection(Section section, Topic topic) {
    	topicDao.openCurrentSessionwithTransaction();
    	boolean exists = topicDao.ifTopicInSection(topic);
    	topicDao.closeCurrentSessionwithTransaction();
    	return exists;
    }
    
	@Transactional
    public void addTopic(Topic topic) {
    	if (topic.getTitle() == null) {
    		throw new RuntimeException("Failed to add topic: null title.");
    	}
    	if (topicDao.ifTopicInSection(topic))
			throw new RuntimeException(String.format("Topic '%s' already exists in section '%s'.",
					topic.getTitle(), topic.getSection().getTitle()));
    	persist(topic);
    }
    
	@Transactional
    public void deleteTopic(Topic topic) {
    	topicDao.openCurrentSessionwithTransaction();
    	topicDao.delete(topic);
    	topicDao.closeCurrentSessionwithTransaction();
    }
 
    public TopicDao topicDao() {
        return topicDao;
    }
}
