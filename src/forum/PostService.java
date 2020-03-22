package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.List;

/**
 * Home object for domain model class Post.
 * @see forum.Post
 * @author Hibernate Tools
 */
public class PostService {
	
	private static PostDao postDao;
	
	public PostService() {
		postDao = new PostDao();
	}

	public void persist(Post transientInstance) {
		postDao.openCurrentSessionwithTransaction();
		postDao.persist(transientInstance);
		postDao.closeCurrentSessionwithTransaction();
	}
	
	public void update(Post entity) {
        postDao.openCurrentSessionwithTransaction();
        postDao.update(entity);
        postDao.closeCurrentSessionwithTransaction();
    }
 
    public Post findById(String id) {
    	postDao.openCurrentSession();
        Post post = postDao.findById(id);
        postDao.closeCurrentSession();
        return post;
    }
 
    public void delete(String id) {
    	postDao.openCurrentSessionwithTransaction();
        Post post = postDao.findById(id);
        postDao.delete(post);
        postDao.closeCurrentSessionwithTransaction();
    }

    public List<Post> findAll() {
        postDao.openCurrentSession();
        List<Post> posts = postDao.findAll();
        postDao.closeCurrentSession();
        return posts;
    }
 
    public void deleteAll() {
    	postDao.openCurrentSessionwithTransaction();
    	postDao.deleteAll();
    	postDao.closeCurrentSessionwithTransaction();
    }
    
    public void createPost(Post entity) {
    	postDao.persist(entity);
    }
 
    public PostDao postDao() {
        return postDao;
    }
}
