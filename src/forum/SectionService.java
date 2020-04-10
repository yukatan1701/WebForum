package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Home object for domain model class Section.
 * @see forum.Section
 * @author Hibernate Tools
 */

@Component
public class SectionService {
	
	private SectionDao sectionDao;
	
	public SectionService(SessionFactory sessionFactory) {
		sectionDao = new SectionDao(sessionFactory);
	}

	@Transactional
	public void persist(Section transientInstance) {
		sectionDao.openCurrentSessionwithTransaction();
		sectionDao.persist(transientInstance);
		sectionDao.closeCurrentSessionwithTransaction();
	}
	
	@Transactional
	public void update(Section entity) {
        sectionDao.openCurrentSessionwithTransaction();
        sectionDao.update(entity);
        sectionDao.closeCurrentSessionwithTransaction();
    }
 
	@Transactional
    public Section findById(Integer id) {
    	sectionDao.openCurrentSession();
        Section section = sectionDao.findById(id);
        sectionDao.closeCurrentSession();
        return section;
    }
    
	@Transactional
    public Section findByTitle(String title) {
    	sectionDao.openCurrentSession();
        Section section = sectionDao.findByTitle(title);
        sectionDao.closeCurrentSession();
        return section;
    }
 
	@Transactional
    public void deleteById(Integer id) {
    	sectionDao.openCurrentSessionwithTransaction();
        Section section = sectionDao.findById(id);
        sectionDao.delete(section);
        sectionDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void delete(Section section) {
    	sectionDao.openCurrentSessionwithTransaction();
        sectionDao.delete(section);
        sectionDao.closeCurrentSessionwithTransaction();
    }

	@Transactional
    public List<Section> findAll() {
        sectionDao.openCurrentSession();
        List<Section> sections = sectionDao.findAll();
        sectionDao.closeCurrentSession();
        return sections;
    }
 
	@Transactional
    public void deleteAll() {
    	sectionDao.openCurrentSessionwithTransaction();
    	sectionDao.deleteAll();
    	sectionDao.closeCurrentSessionwithTransaction();
    }
    
	@Transactional
    public void addSection(Section entity) {
		persist(entity);
    }
    
	@Transactional
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
