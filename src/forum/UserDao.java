package forum;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public class UserDao extends DBSession implements DaoInterface<User, Integer> {
	
	public UserDao(SessionFactory sessionFactory) {
		super(UserDao.class.getName());
		this.sessionFactory = sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	@Override
    public void persist(User entity) {
		logger.log(Level.INFO, "persisting User instance");
		try {
			this.sessionFactory.getCurrentSession().save(entity);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			try {
				this.sessionFactory.getCurrentSession().getTransaction().rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "rollback failed", e);
			}
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
			try {
				getCurrentSession().getTransaction().rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "rollback failed", e);
			}
			throw re;
		}
    }
    
	@Override
    public User findById(Integer id) {
		logger.log(Level.INFO, "getting User instance with id: " + id);
		try {
			User user = (User) this.sessionFactory.getCurrentSession().get(User.class, id);
			if (user == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return user; 
		} catch (RuntimeException re) {
			logger.log(Level.INFO, "get failed", re);
			return null;
		}
    }
	
	@SuppressWarnings("deprecation")
	public User findByLogin(String login) {
		logger.log(Level.INFO, "getting User instance with login: " + login);
		try {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
			criteria.add(Restrictions.like("login", login));
			User user = (User) criteria.uniqueResult();
			return user;
		} catch (RuntimeException re) {
			logger.log(Level.INFO, "get failed", re);
			return null;
		}
	}
 
	@Override
    public void delete(User entity) {
		logger.log(Level.INFO, "deleting User instance");
		try {
			this.sessionFactory.getCurrentSession().delete(entity);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			try {
				this.sessionFactory.getCurrentSession().getTransaction().rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "rollback failed", e);
			}
			throw re;
		}
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
    	logger.log(Level.INFO, "finding all on User instance");
		try {
			List<User> users = (List<User>) currentSession.createQuery("FROM User").list();
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
