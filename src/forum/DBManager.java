package forum;

import java.util.*;
import java.text.SimpleDateFormat;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class DBManager {

	public static void main(String[] args) {
		/*Session s = new Configuration().configure().buildSessionFactory().getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> l = s.createQuery("FROM User").list();
		for (User u : l) {
			System.out.println("ID:" + u.getUserId()
							+ "; login: " + u.getLogin()
							+ "; date of reg: " + u.getDateOfRegistration()
							+ "; Permission: " + u.getPermissions()
							+ "; Status: " + u.getStatus());
		}*/
		UserService userService = new UserService();
		List<User> all = userService.findAll();
		User medved = null;
		for (User user : all) {
			if (user.getLogin().equals("medved")) {
				medved = user;
				break;
			}
		}
		if (medved == null) {
			System.out.println("Failed to find medved!");
			return;
		}
		try {
			medved.setDateOfRegistration(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.update(medved);
	}

}
