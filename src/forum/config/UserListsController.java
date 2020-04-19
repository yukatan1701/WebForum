package forum.config;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.Section;
import forum.SectionService;
import forum.User;
import forum.UserService;
import forum.enums.Permissions;
import forum.enums.Status;

@Controller
public class UserListsController {
	@Autowired
	private UserService userService;
	@Autowired
	private SectionService sectionService;
	
	private ModelAndView loadPage(ModelAndView model, String dateBegin, String dateEnd) {
		System.out.println(dateBegin + " " + dateEnd);
		Date begin = Date.valueOf(dateBegin);
		Date end = Date.valueOf(dateEnd);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		LinkedHashMap<User, Integer> activeUsers = userService.getActiveUsers(begin, end);
		model.addObject("userMap", activeUsers);
		HashMap<Section, HashSet<User>> usersBySections = sectionService.getUsersBySections();
		model.addObject("usersBySectionsList", usersBySections);
		List<User> users = userService.findAll();
		model.addObject("userList", users);
		model.addObject("dateBegin", dateFormat.format(begin));
		model.addObject("dateEnd", dateFormat.format(end));
		return model;
	}
	/*
	@RequestMapping(value = "/user_lists", method = RequestMethod.GET)
	public ModelAndView user_lists() {
		String begin = "2010-01-01", end = "2030-01-01";
		return loadPage(begin, end);
	}*/
	
	@RequestMapping(value = "/user_lists")
	public ModelAndView user_lists(@RequestParam(value = "date_begin", required=false) String dateBegin,
			@RequestParam(value="date_end", required=false) String dateEnd,
			@RequestParam(value="error_type", required=false) String error) {
		if (dateBegin == null) {
			dateBegin = "2010-01-01";
		}
		if (dateEnd == null) {
			dateEnd = "2030-01-01";
		}
		if (java.sql.Date.valueOf(dateBegin).after(java.sql.Date.valueOf(dateEnd))) {
			error = "bad_date";
		}
		ModelAndView model = new ModelAndView("user_lists");
		if (error != null) {
			if (error.equals("bad_permissions")) {
				model.addObject("add_user_error", "Недостаточно прав");
			} else if (error.equals("bad_date")) {
				model.addObject("date_error", "Дата начала больше даты конца");
			}
		}
		return loadPage(model, dateBegin, dateEnd);
	}
	
	@RequestMapping(value= "/user_lists/add", method = RequestMethod.POST)
	public String addUser(@RequestParam("login") String login, @RequestParam("password") String password,
			@RequestParam("permissions") String perms, @RequestParam("current_login") String currentLogin) {
		User currentUser = userService.findByLogin(currentLogin);
		if (currentUser.getPermissions() == Permissions.USER) {
			return "redirect:/user_lists?error_type=bad_permissions";
		}
		Permissions perm = perms.equals("user") ? Permissions.USER : Permissions.MODERATOR;
		java.util.Date date = new java.util.Date();
		User user = new User(login, Md5PasswordEncoder.getMD5(password), new Date(date.getTime()), perm, Status.NORMAL);
		userService.addUser(user);
		return "redirect:/user_lists";
	}
}
