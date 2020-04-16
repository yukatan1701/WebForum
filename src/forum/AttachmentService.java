package forum;
// Generated 26.02.2020 21:11:44 by Hibernate Tools 5.4.7.Final

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Attachment.
 * @see forum.Attachment
 * @author Hibernate Tools
 */

@Component
public class AttachmentService {
	
	private static AttachmentDao attachmentDao;
	
	public AttachmentService(SessionFactory sessionFactory) {
		attachmentDao = new AttachmentDao(sessionFactory);
	}

	@Transactional
	public void persist(Attachment transientInstance) {
		attachmentDao.openCurrentSessionwithTransaction();
		attachmentDao.persist(transientInstance);
		attachmentDao.closeCurrentSessionwithTransaction();
	}
	
	@Transactional
	public void update(Attachment entity) {
        attachmentDao.openCurrentSessionwithTransaction();
        attachmentDao.update(entity);
        attachmentDao.closeCurrentSessionwithTransaction();
    }
 
	@Transactional
    public Attachment findById(Integer id) {
    	attachmentDao.openCurrentSession();
        Attachment attachment = attachmentDao.findById(id);
        attachmentDao.closeCurrentSession();
        return attachment;
    }
 
	@Transactional
    public void deleteById(Integer id) {
    	attachmentDao.openCurrentSessionwithTransaction();
        Attachment attachment = attachmentDao.findById(id);
        attachmentDao.delete(attachment);
        attachmentDao.closeCurrentSessionwithTransaction();
    }

	@Transactional
    public List<Attachment> findAll() {
        attachmentDao.openCurrentSession();
        List<Attachment> attachments = attachmentDao.findAll();
        attachmentDao.closeCurrentSession();
        return attachments;
    }
 
	@Transactional
    public void deleteAll() {
    	attachmentDao.openCurrentSessionwithTransaction();
    	attachmentDao.deleteAll();
    	attachmentDao.closeCurrentSessionwithTransaction();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
	public void addAttachment(Attachment attachment) {
    	//attachment.getPost().getAttachments().add(attachment);
    	persist(attachment);
    }
    
    @Transactional
    public void delete(Attachment attachment) {
    	attachmentDao.openCurrentSessionwithTransaction();
    	attachmentDao.delete(attachment);
    	attachmentDao.closeCurrentSessionwithTransaction();
    }
 
    public AttachmentDao attachmentDao() {
        return attachmentDao;
    }
}
