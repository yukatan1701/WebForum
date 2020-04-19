package forum.config;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.Section;
import forum.SectionService;
import forum.User;
import forum.UserService;
import forum.enums.Permissions;

@Controller
public class SectionsController {
	@Autowired
	private UserService userService;
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap map) {
        return "redirect:/sections";
    }
	
	private ModelAndView loadPage(ModelAndView model) {
		System.out.println(System.getProperty("catalina.base"));
		System.out.println(System.getProperty("com.sun.aas.instanceRoot"));
		LinkedHashMap<User, Integer> all_users = userService.getActiveUsers(Date.valueOf("2010-01-01"), Date.valueOf("2030-01-01"));
		LinkedHashMap<User, Integer> users = new LinkedHashMap<User, Integer>();
		int limit = 5;
		for (Entry<User, Integer> entry : all_users.entrySet()) {
			if (limit == 0) {
				break;
			}
		    users.put(entry.getKey(), entry.getValue());
		    limit--;
		}
		
		model.addObject("userMap", users);
		List<Section> sections = sectionService.findAll();
		model.addObject("sectionList", sections);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
	
	@RequestMapping(value = "/sections", method = RequestMethod.GET)
	public ModelAndView sections(@RequestParam(value="error_type", required=false) String error) {
		ModelAndView model = new ModelAndView("sections");
		if (error != null) {
			if (error.equals("bad_permissions")) {
				model.addObject("add_section_error", "Недостаточно прав");
			} else if (error.equals("bad_permissions_delete")) {
				model.addObject("delete_section_error", "Недостаточно прав");
			} else if (error.equals("existing_section")) {
				model.addObject("add_section_error", "Секция с таким именем уже существует");
			}
		}
		return loadPage(model);
	}
	
	@RequestMapping(value= "/sections/add_section", method = RequestMethod.POST)
	public String addSection(@RequestParam("title") String title, @RequestParam("login") String login) {
		User user = userService.findByLogin(login);
		if (user.getPermissions() == Permissions.USER) {
			return "redirect:/sections?error_type=bad_permissions";
		}
		try {
			Section s = new Section(title);
			this.sectionService.addSection(s);
		} catch (org.hibernate.exception.ConstraintViolationException ex) {
			return "redirect:/sections?error_type=existing_section";
		}
		return "redirect:/sections";
	}
	
	@RequestMapping(value="/sections/delete_section")
	public String deleteSection(@RequestParam("id") int id, @RequestParam("login") String login) {
		User user = userService.findByLogin(login);
		if (user.getPermissions() == Permissions.USER) {
			return "redirect:/sections?error_type=bad_permissions_delete";
		}
		sectionService.deleteById(id);
		return "redirect:/sections";       
	}
}
