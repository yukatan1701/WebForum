package forum;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.hibernate.SessionFactory;

public class TopicDao extends DBSession implements DaoInterface<Topic, Integer>{
	
	public TopicDao(SessionFactory sessionFactory) {
		super(TopicDao.class.getName());
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void persist(Topic entity) {
		logger.log(Level.INFO, "persisting Topic instance");
		try {
			getCurrentSession().save(entity);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			try {
				currentTransaction.rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "Couldn't roll back transaction.", e);
			}
			throw re;
		}
	}

	@Override
	public void update(Topic entity) {
		logger.log(Level.INFO, "updating Topic instance");
		try {
			getCurrentSession().update(entity);
			logger.log(Level.INFO, "update successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "update failed", re);
			try {
				currentTransaction.rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "Couldn't roll back transaction.", e);
			}
			throw re;
		}
	}

	@Override
	public Topic findById(Integer id) {
		logger.log(Level.INFO, "getting Topic instance with id: " + id);
		try {
			Topic topic = (Topic) getCurrentSession().get(Topic.class, id);
			if (topic == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return topic; 
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			return null;
		}
	}

	@Override
	public void delete(Topic entity) {
		logger.log(Level.INFO, "deleting Topic instance");
		try {
			getCurrentSession().delete(entity);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			try {
				currentTransaction.rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "Couldn't roll back transaction.", e);
			}
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findAll() {
		logger.log(Level.INFO, "finding all on Topic instance");
		try {
			List<Topic> topics = (List<Topic>) getCurrentSession().createQuery("FROM Topic").list();
			logger.log(Level.INFO, "find all successful");
			return topics;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find all failed", re);
			throw re;
		}
	}

	@Override
	public void deleteAll() {
		logger.log(Level.INFO, "deleting all on Topic instance");
		try {
			List<Topic> entityList = findAll();
	        for (Topic entity : entityList) {
	            delete(entity);
	        }
	        logger.log(Level.INFO, "delete all successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete all failed", re);
			try {
				currentTransaction.rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "Couldn't roll back transaction.", e);
			}
			throw re;
		}
	}
	
    @SuppressWarnings("unchecked")
	public boolean ifTopicInSection(Topic topic) {
    	boolean exists = false;
    	if (topic == null || topic.getSection() == null)
    		throw new RuntimeException("Failed to check if topic is in section: topic or section is null.");
    	for (Topic top : (Set<Topic>) topic.getSection().getTopics()) {
    		if (topic.getTitle().equals(top.getTitle())) {
    			exists = true;
    			break;
    		}
    	}
    	return exists;
    }

}
