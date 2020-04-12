package forum.config;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.Section;
import forum.User;

@Controller
public class LoginController {
	
	
	
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView("welcomePage");
		return model;
	}
	
	@RequestMapping(value = { "/sectionsPage"}, method = RequestMethod.GET)
	public ModelAndView sectionsPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("sectionsPage");
		return model;
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
	@RequestParam(value = "logout",	required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from WebForum successfully.");
		}

		model.setViewName("loginPage");
		return model;
	}*/
}
