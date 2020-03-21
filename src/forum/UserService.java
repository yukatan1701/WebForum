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
 * Home object for domain model class User.
 * @see forum.User
 * @author Hibernate Tools
 */
public class UserService {
	
	private static UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}

	public void persist(User transientInstance) {
		userDao.openCurrentSessionwithTransaction();
		userDao.persist(transientInstance);
		userDao.closeCurrentSessionwithTransaction();
	}
	
	public void update(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }
 
    public User findById(String id) {
    	userDao.openCurrentSession();
        User user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }
 
    public void delete(String id) {
    	userDao.openCurrentSessionwithTransaction();
        User user = userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.closeCurrentSession();
        return users;
    }
 
    public void deleteAll() {
    	userDao.openCurrentSessionwithTransaction();
    	userDao.deleteAll();
    	userDao.closeCurrentSessionwithTransaction();
    }
 
    public UserDao userDao() {
        return userDao;
    }
}
