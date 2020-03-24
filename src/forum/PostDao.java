package forum;

import java.util.List;
import java.util.logging.Level;

public class PostDao extends DBSession implements DaoInterface<Post, Integer> {
	
	public PostDao() {
		super(PostDao.class.getName());
	}
	
	@Override
    public void persist(Post entity) {
		logger.log(Level.INFO, "persisting Post instance");
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
    public void update(Post entity) {
		logger.log(Level.INFO, "updating Post instance");
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
    public Post findById(Integer id) {
		logger.log(Level.INFO, "getting Post instance with id: " + id);
		try {
			Post post = (Post) getCurrentSession().get(Post.class, id);
			if (post == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return post; 
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			return null;
		}
    }
 
	@Override
    public void delete(Post entity) {
		logger.log(Level.INFO, "deleting Post instance");
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
    public List<Post> findAll() {
    	logger.log(Level.INFO, "finding all on Post instance");
		try {
			List<Post> posts = (List<Post>) getCurrentSession().createQuery("FROM Post").list();
			logger.log(Level.INFO, "find all successful");
			return posts;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find all failed", re);
			throw re;
		}
    }

    @Override
    public void deleteAll() {
    	logger.log(Level.INFO, "deleting all on Post instance");
		try {
	        List<Post> entityList = findAll();
	        for (Post entity : entityList) {
	            delete(entity);
	        }
	        logger.log(Level.INFO, "delete all successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete all failed", re);
			throw re;
		}
    }
}
