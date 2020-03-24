package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Home object for domain model class Section.
 * @see forum.Section
 * @author Hibernate Tools
 */
public class SectionService {
	
	private static SectionDao sectionDao;
	
	public SectionService() {
		sectionDao = new SectionDao();
	}

	public void persist(Section transientInstance) {
		sectionDao.openCurrentSessionwithTransaction();
		sectionDao.persist(transientInstance);
		sectionDao.closeCurrentSessionwithTransaction();
	}
	
	public void update(Section entity) {
        sectionDao.openCurrentSessionwithTransaction();
        sectionDao.update(entity);
        sectionDao.closeCurrentSessionwithTransaction();
    }
 
    public Section findById(Integer id) {
    	sectionDao.openCurrentSession();
        Section section = sectionDao.findById(id);
        sectionDao.closeCurrentSession();
        return section;
    }
    
    public Section findByTitle(String title) {
    	sectionDao.openCurrentSession();
        Section section = sectionDao.findByTitle(title);
        sectionDao.closeCurrentSession();
        return section;
    }
 
    public void deleteById(Integer id) {
    	sectionDao.openCurrentSessionwithTransaction();
        Section section = sectionDao.findById(id);
        sectionDao.delete(section);
        sectionDao.closeCurrentSessionwithTransaction();
    }
    
    public void delete(Section section) {
    	sectionDao.openCurrentSessionwithTransaction();
        sectionDao.delete(section);
        sectionDao.closeCurrentSessionwithTransaction();
    }

    public List<Section> findAll() {
        sectionDao.openCurrentSession();
        List<Section> sections = sectionDao.findAll();
        sectionDao.closeCurrentSession();
        return sections;
    }
 
    public void deleteAll() {
    	sectionDao.openCurrentSessionwithTransaction();
    	sectionDao.deleteAll();
    	sectionDao.closeCurrentSessionwithTransaction();
    }
    
    public void addSection(Section entity) {
		persist(entity);
    }
    
    @SuppressWarnings("unchecked")
	public HashMap<Section, HashSet<User>> getUsersBySections() {
    	sectionDao.openCurrentSession();
    	List<Section> sections = sectionDao.findAll();
    	HashMap<Section, HashSet<User>> usersInSections = new HashMap<>(0);
    	for (Section section : sections) {
    		Set<Topic> topics = section.getTopics();
    		HashSet<User> sectionUsers = new HashSet<>(0);
    		for (Topic topic : topics) {
    			Set<Post> posts = topic.getPosts();
    			for (Post post: posts) {
    				sectionUsers.add(post.getUser());
    			}
    		}
    		usersInSections.put(section, sectionUsers);
    	}
    	sectionDao.closeCurrentSession();
    	return usersInSections;
    }
 
    public SectionDao sectionDao() {
        return sectionDao;
    }
}
