package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import forum.enums.Status;

import java.util.Map.Entry;

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
 
    public User findById(Integer id) {
    	userDao.openCurrentSession();
        User user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }
 
    public void delete(Integer id) {
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
    
    public void addUser(User user) {
    	update(user);
    }
    
    public LinkedHashMap<User, Integer> getActiveUsers(Date begin, Date end) {
    	userDao.openCurrentSession();
    	List<User> users = userDao.findAll();
    	HashMap<User, Integer> activeUsers = new HashMap<>(0);
    	for (User user : users) {
    		int postsPerDate = 0;
    		@SuppressWarnings("unchecked")
			Set<Post> posts = user.getPosts();
    		for (Post post : posts) {
    			if (post.getDatetime().after(begin) && post.getDatetime().before(end)) {
    				postsPerDate++;
    			}
    		}
    		if (postsPerDate > 0) {
    			activeUsers.put(user, postsPerDate);
    		}
    		//System.out.printf("[%s]: %d messages\n", user.getLogin(), user.getPosts().size());
    	}
    	userDao.closeCurrentSession();
		LinkedHashMap<User, Integer> sortedMap = new LinkedHashMap<>();
		activeUsers.entrySet().stream().sorted(Map.Entry.comparingByValue(
				Comparator.reverseOrder())).forEachOrdered(
				x -> sortedMap.put(x.getKey(), x.getValue()));
    	return sortedMap;
    }
 
    void blockUser(User user) {
    	userDao.openCurrentSessionwithTransaction();
    	user.setStatus(Status.BLOCKED);
    	userDao.update(user);
    	userDao.closeCurrentSessionwithTransaction();
    }
    
    void unblockUser(User user) {
    	userDao.openCurrentSessionwithTransaction();
    	user.setStatus(Status.NORMAL);
    	userDao.update(user);
    	userDao.closeCurrentSessionwithTransaction();
    }
    
    public UserDao userDao() {
        return userDao;
    }
}