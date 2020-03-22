package forum;

import java.util.List;
import java.util.logging.Level;

public class SectionDao extends DBSession implements DaoInterface<Section, Integer> {
	
	public SectionDao() {
		super(SectionDao.class.getName());
	}
	
	@Override
    public void persist(Section entity) {
		logger.log(Level.INFO, "persisting Section instance");
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
    public void update(Section entity) {
		logger.log(Level.INFO, "updating Section instance");
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
    public Section findById(Integer id) {
		logger.log(Level.INFO, "getting Section instance with id: " + id);
		try {
			Section section = (Section) getCurrentSession().get(Section.class, id);
			if (section == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return section; 
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
    }
 
	@Override
    public void delete(Section entity) {
		logger.log(Level.INFO, "deleting Section instance");
		try {
			getCurrentSession().delete(entity);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			try {
				currentTransaction.rollback();
			} catch (RuntimeException e) {
				logger.log(Level.SEVERE, "Couldn't roll back transaction.", e);
			}
			throw re;
		}
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Section> findAll() {
    	logger.log(Level.INFO, "finding all on Section instance");
		try {
			List<Section> sections = (List<Section>) getCurrentSession().createQuery("FROM Section").list();
			logger.log(Level.INFO, "find all successful");
			return sections;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find all failed", re);
			throw re;
		}
    }

    @Override
    public void deleteAll() {
    	logger.log(Level.INFO, "deleting all on Section instance");
		try {
	        List<Section> entityList = findAll();
	        for (Section entity : entityList) {
	            delete(entity);
	        }
	        logger.log(Level.INFO, "delete all successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete all failed", re);
			try {
				currentTransaction.rollback();
			} catch (RuntimeException e) {
				System.err.printf("Couldn't roll back transaction.", e);
			}
			throw re;
		}
    }
}
