package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Post.
 * @see forum.Post
 * @author Hibernate Tools
 */

@Component
public class PostService {
	
	private static PostDao postDao;
	
	public PostService(SessionFactory sessionFactory) {
		postDao = new PostDao(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void persist(Post post) {
		postDao.openCurrentSessionwithTransaction();
		postDao.persist(post);
		post.getTopic().getPosts().add(post);
		post.getUser().getPosts().add(post);
		postDao.closeCurrentSessionwithTransaction();
	}
	
	@Transactional
	public void update(Post entity) {
        postDao.openCurrentSessionwithTransaction();
        postDao.update(entity);
        postDao.closeCurrentSessionwithTransaction();
    }
 
	@Transactional
    public Post findById(Integer id) {
    	postDao.openCurrentSession();
        Post post = postDao.findById(id);
        postDao.closeCurrentSession();
        return post;
    }
 
	@Transactional
    public void deleteById(Integer id) {
    	postDao.openCurrentSessionwithTransaction();
        Post post = postDao.findById(id);
        postDao.delete(post);
        postDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void delete(Post post) {
    	postDao.openCurrentSessionwithTransaction();
        postDao.delete(post);
        postDao.closeCurrentSessionwithTransaction();
    }

	@Transactional
    public List<Post> findAll() {
        postDao.openCurrentSession();
        List<Post> posts = postDao.findAll();
        postDao.closeCurrentSession();
        return posts;
    }
 
	@Transactional
    public void deleteAll() {
    	postDao.openCurrentSessionwithTransaction();
    	postDao.deleteAll();
    	postDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void addPost(Post post) {
    	persist(post);
    }
    
	@Transactional
    public void deletePost(Post post) {
    	postDao.openCurrentSessionwithTransaction();
    	postDao.delete(post);
    	postDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    @SuppressWarnings("unchecked")
	public void printAttachments() {
    	postDao.openCurrentSession();
    	List<Post> posts = postDao.findAll();
    	for (Post post : posts) {
    		Set<Attachment> ats = post.getAttachments();
    		if (ats.size() == 0) {
    			System.out.printf("[%d]: no links.\n", post.getPostId());
    			continue;
    		}
    		for (Attachment at : ats) {
    			System.out.printf("[%d][%s]: %s\n", post.getPostId(), post.getText(),at.getFileLink());
    		}
    	}
    	postDao.closeCurrentSession();
    }
 
    public PostDao postDao() {
        return postDao;
    }
}
