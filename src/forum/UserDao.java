package forum;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class UserDao extends DBSession implements DaoInterface<User, Integer> {
	
	public UserDao() {
		super(UserDao.class.getName());
	}
	
	@Override
    public void persist(User entity) {
		logger.log(Level.INFO, "persisting User instance");
		try {
			getCurrentSession().save(entity);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
    }
 
	@Override
    public void update(User entity) {
		logger.log(Level.INFO, "updating User instance");
		try {
			getCurrentSession().update(entity);
			logger.log(Level.INFO, "update successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "update failed", re);
			throw re;
		}
    }
    
	@Override
    public User findById(Integer id) {
		logger.log(Level.INFO, "getting User instance with id: " + id);
		try {
			User user = (User) getCurrentSession().get(User.class, id);
			if (user == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return user; 
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
    }
 
	@Override
    public void delete(User entity) {
		logger.log(Level.INFO, "deleting User instance");
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
    public List<User> findAll() {
    	logger.log(Level.INFO, "finding all on User instance");
		try {
			List<User> users = (List<User>) getCurrentSession().createQuery("FROM User").list();
			logger.log(Level.INFO, "find all successful");
			return users;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find all failed", re);
			throw re;
		}
    }

    @Override
    public void deleteAll() {
    	logger.log(Level.INFO, "deleting all on User instance");
		try {
	        List<User> entityList = findAll();
	        for (User entity : entityList) {
	            delete(entity);
	        }
	        logger.log(Level.INFO, "delete all successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete all failed", re);
			throw re;
		}
    }
}
