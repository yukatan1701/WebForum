package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import forum.enums.Status;

@Component
public class UserService implements UserDetailsService {
	
	private UserDao userDao;
	
	public UserService(SessionFactory sessionFactory) {
		this.userDao = new UserDao(sessionFactory);
	}

	@Transactional
	public void persist(User transientInstance) {
		userDao.openCurrentSessionwithTransaction();
		userDao.persist(transientInstance);
		userDao.closeCurrentSessionwithTransaction();
	}
	
	@Transactional
	public void update(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }
 
	@Transactional
    public User findById(Integer id) {
    	userDao.openCurrentSession();
        User user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }
    
	@Transactional
    public User findByLogin(String login) {
    	userDao.openCurrentSession();
    	User user = userDao.findByLogin(login);
    	userDao.closeCurrentSession();
    	return user;
    }
 
	@Transactional
    public void deleteById(Integer id) {
    	userDao.openCurrentSessionwithTransaction();
        User user = userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void delete(User user) {
    	userDao.openCurrentSessionwithTransaction();
    	userDao.delete(user);
    	userDao.closeCurrentSessionwithTransaction();
    }

	@Transactional
    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.closeCurrentSession();
        return users;
    }
 
	@Transactional
    public void deleteAll() {
    	userDao.openCurrentSessionwithTransaction();
    	userDao.deleteAll();
    	userDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void addUser(User user) {
    	persist(user);
    }
    
	@Transactional
    public LinkedHashMap<User, Integer> getActiveUsers(Date begin, Date end) {
    	userDao.openCurrentSession();
    	List<User> users = userDao.findAll();
    	HashMap<User, Integer> activeUsers = new HashMap<>(0);
    	for (User user : users) {
    		int postsPerDate = 0;
    		@SuppressWarnings("unchecked")
			Set<Post> posts = user.getPosts();
    		for (Post post : posts) {
    			if (post.getDatetime().compareTo(begin) >= 0 && post.getDatetime().compareTo(end) < 0) {
    				postsPerDate++;
    			}
    		}
    		if (postsPerDate > 0) {
    			activeUsers.put(user, postsPerDate);
    		}
    	}
		LinkedHashMap<User, Integer> sortedMap = new LinkedHashMap<>();
		activeUsers.entrySet().stream().sorted(Map.Entry.comparingByValue(
				Comparator.reverseOrder())).forEachOrdered(
				x -> sortedMap.put(x.getKey(), x.getValue()));
		userDao.closeCurrentSession();
    	return sortedMap;
    }
 
	@Transactional
    public void blockUser(User user) {
    	userDao.openCurrentSessionwithTransaction();
    	user.setStatus(Status.BLOCKED);
    	userDao.update(user);
    	userDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void unblockUser(User user) {
    	userDao.openCurrentSessionwithTransaction();
    	user.setStatus(Status.NORMAL);
    	userDao.update(user);
    	userDao.closeCurrentSessionwithTransaction();
    }
    
    public UserDao userDao() {
        return userDao;
    }
    
    @SuppressWarnings("serial")
	@Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException
    {
    	User user = findByLogin(username);
        Collection<GrantedAuthority> auts = new ArrayList<GrantedAuthority>();
        auts.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (user == null)
        	throw new UsernameNotFoundException("Failed to find username " + username);
        System.out.println("User: " + user.getLogin() + "; password: " + user.getPassword());
        UserDetails userDet = new org.springframework.security.core.userdetails.User(user.getLogin(), "{noop}" + user.getPassword(), true, true, true, true, auts) {};
        return userDet;
    }
}
