package forum;

import java.util.List;
import java.util.logging.Level;

public class AttachmentDao extends DBSession implements DaoInterface<Attachment, Integer> {
	
	public AttachmentDao() {
		super(AttachmentDao.class.getName());
	}
	
	@Override
    public void persist(Attachment entity) {
		logger.log(Level.INFO, "persisting Attachment instance");
		try {
			getCurrentSession().save(entity);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
    }
 
	@Override
    public void update(Attachment entity) {
		logger.log(Level.INFO, "updating Attachment instance");
		try {
			getCurrentSession().update(entity);
			logger.log(Level.INFO, "update successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "update failed", re);
			throw re;
		}
    }
    
	@Override
    public Attachment findById(Integer id) {
		logger.log(Level.INFO, "getting Attachment instance with id: " + id);
		try {
			Attachment attachment = (Attachment) getCurrentSession().get(Attachment.class, id);
			if (attachment == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return attachment; 
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
    }
 
	@Override
    public void delete(Attachment entity) {
		logger.log(Level.INFO, "deleting Attachment instance");
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
    public List<Attachment> findAll() {
    	logger.log(Level.INFO, "finding all on Attachment instance");
		try {
			List<Attachment> attachments = (List<Attachment>) getCurrentSession().createQuery("FROM Attachment").list();
			logger.log(Level.INFO, "find all successful");
			return attachments;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find all failed", re);
			throw re;
		}
    }

    @Override
    public void deleteAll() {
    	logger.log(Level.INFO, "deleting all on Attachment instance");
		try {
	        List<Attachment> entityList = findAll();
	        for (Attachment entity : entityList) {
	            delete(entity);
	        }
	        logger.log(Level.INFO, "delete all successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete all failed", re);
			throw re;
		}
    }
}
