package forum;
import forum.enums.*;

import java.util.*;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class App {

	public static void main(String[] args) {
		//Session s = new Configuration().configure().buildSessionFactory().getCurrentSession();
		
		SectionService sectionService = new SectionService();
		//Section sec = new Section("Some text");
		//sectionService.createSection(sec);
		//Section sec2 = new Section("Zero");
		//sectionService.deleteSection(sec2);
		//sectionService.delete(6);
		UserService userService = new UserService();
		Date begin, end;
		try {
			begin = new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01");
			end = new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01");
			LinkedHashMap<User, Integer> activeUsers = userService.getActiveUsers(begin, end);
			for (User user : activeUsers.keySet()) {
				System.out.printf("[%s]: %d posts.\n", user.getLogin(), activeUsers.get(user));
			}
			System.out.println("END!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = userService.findById(3);
		System.out.printf("(%s): %s\n", user.getLogin(), user.getStatus());
		userService.blockUser(user);
		System.out.printf("(%s): %s\n", user.getLogin(), user.getStatus());
		userService.unblockUser(user);
		System.out.printf("(%s): %s\n", user.getLogin(), user.getStatus());
	}

}
