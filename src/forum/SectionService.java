package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
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
 
    public void delete(Integer id) {
    	sectionDao.openCurrentSessionwithTransaction();
        Section section = sectionDao.findById(id);
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
    
    public void createSection(Section entity) {
    	try {
    		persist(entity);
    	} catch (RuntimeException e) {
    		Throwable cause = e.getCause();
    		if (cause instanceof SQLException) {
    			String state = ((SQLException) cause).getSQLState();
    			if (state.equals("23505")) {
    				System.out.printf("Section '%s' already exists.\n", entity.getTitle());
    				return;
    			}
    		}
    		throw e;
    	}
    }
    
    public void deleteSection(Section entity) {
    	sectionDao.openCurrentSessionwithTransaction();
    	sectionDao.delete(entity);
    	sectionDao.closeCurrentSessionwithTransaction();
    }
 
    public SectionDao sectionDao() {
        return sectionDao;
    }
}
