package forum;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBSession {
	protected Session currentSession;
	protected Transaction currentTransaction;
	protected SessionFactory sessionFactory;
	
	protected static Logger logger;
	
	public DBSession(String classname) {
		logger = Logger.getLogger(classname);
	}
	
	public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    
    private static SessionFactory getSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			return sessionFactory;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not build session factory", e);
			throw new IllegalStateException("Could not build session factory");
		}
	}
    
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}
