package forum;

import forum.enums.*;

import java.util.*;
import java.text.SimpleDateFormat;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		//Session s = new Configuration().configure().buildSessionFactory().getCurrentSession();
		
		SectionService sectionService = new SectionService();
		//Section sec = new Section("Some text");
		//sectionService.createSection(sec);
		//Section sec2 = new Section("Zero");
		//sectionService.deleteSection(sec2);
		sectionService.delete(6);
	}

}
