package forum;

import java.util.List;
import java.util.logging.Level;

public class TopicDao extends DBSession implements DaoInterface<Topic, Integer>{
	
	public TopicDao() {
		super(TopicDao.class.getName());
	}
	
	@Override
	public void persist(Topic entity) {
		logger.log(Level.INFO, "persisting Topic instance");
		try {
			getCurrentSession().save(entity);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
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
			throw re;
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
			throw re;
		}
	}

}
