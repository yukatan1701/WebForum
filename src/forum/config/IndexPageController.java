package forum.config;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import forum.Section;
import forum.SectionService;
import forum.User;
import forum.UserService;

@Controller
public class IndexPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		LinkedHashMap<User, Integer> users = userService.getActiveUsers(Date.valueOf("2010-01-01"), Date.valueOf("2030-01-01"));
		ModelAndView model = new ModelAndView("index");
		model.addObject("userMap", users);
		List<Section> sections = sectionService.findAll();
		model.addObject("sectionList", sections);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
	
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	public String addSection(Section s) {
		this.sectionService.addSection(s);
		return "redirect:/";
	}
}
