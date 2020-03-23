package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.List;

/**
 * Home object for domain model class Attachment.
 * @see forum.Attachment
 * @author Hibernate Tools
 */
public class AttachmentService {
	
	private static AttachmentDao attachmentDao;
	
	public AttachmentService() {
		attachmentDao = new AttachmentDao();
	}

	public void persist(Attachment transientInstance) {
		attachmentDao.openCurrentSessionwithTransaction();
		attachmentDao.persist(transientInstance);
		attachmentDao.closeCurrentSessionwithTransaction();
	}
	
	public void update(Attachment entity) {
        attachmentDao.openCurrentSessionwithTransaction();
        attachmentDao.update(entity);
        attachmentDao.closeCurrentSessionwithTransaction();
    }
 
    public Attachment findById(Integer id) {
    	attachmentDao.openCurrentSession();
        Attachment attachment = attachmentDao.findById(id);
        attachmentDao.closeCurrentSession();
        return attachment;
    }
 
    public void delete(Integer id) {
    	attachmentDao.openCurrentSessionwithTransaction();
        Attachment attachment = attachmentDao.findById(id);
        attachmentDao.delete(attachment);
        attachmentDao.closeCurrentSessionwithTransaction();
    }

    public List<Attachment> findAll() {
        attachmentDao.openCurrentSession();
        List<Attachment> attachments = attachmentDao.findAll();
        attachmentDao.closeCurrentSession();
        return attachments;
    }
 
    public void deleteAll() {
    	attachmentDao.openCurrentSessionwithTransaction();
    	attachmentDao.deleteAll();
    	attachmentDao.closeCurrentSessionwithTransaction();
    }
    
    public void createAttachment(Attachment attachment) {
    	persist(attachment);
    }
    
    public void deleteAttachment(Attachment attachment) {
    	attachmentDao.openCurrentSessionwithTransaction();
    	attachmentDao.delete(attachment);
    	attachmentDao.closeCurrentSessionwithTransaction();
    }
 
    public AttachmentDao attachmentDao() {
        return attachmentDao;
    }
}
