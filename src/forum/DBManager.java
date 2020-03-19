package forum;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class DBManager {

	public static void main(String[] args) {
		Session s = new Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> l = s.createQuery("FROM User").list();
		for (User u : l) {
			System.out.println("ID:" + u.getUserId()
							+ "; login: " + u.getLogin()
							+ "; date of reg: " + u.getDateOfRegistration()
							+ "; Permission: " + u.getPermissions()
							+ "; Status: " + u.getStatus());
		}
	}

}
