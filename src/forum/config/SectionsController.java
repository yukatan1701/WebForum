package forum.config;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap map) {
        return "redirect:/sections";
    }
	 
	@RequestMapping(value = "/sections", method = RequestMethod.GET)
	public ModelAndView sections() {
		LinkedHashMap<User, Integer> users = userService.getActiveUsers(Date.valueOf("2010-01-01"), Date.valueOf("2030-01-01"));
		ModelAndView model = new ModelAndView("sections");
		model.addObject("userMap", users);
		List<Section> sections = sectionService.findAll();
		model.addObject("sectionList", sections);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
	
	@RequestMapping(value= "/sections/add_section", method = RequestMethod.POST)
	public String addSection(@ModelAttribute("section") Section s) {
		this.sectionService.addSection(s);
		return "redirect:/sections";
	}
	
	@RequestMapping(value="/sections/delete_section")
	public String deleteSection(@RequestParam("id") int id) {
		sectionService.deleteById(id);
		return "redirect:/sections";       
	}
}
