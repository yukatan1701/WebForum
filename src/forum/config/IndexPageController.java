package forum.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import forum.User;
import forum.UserDao;

@Controller
public class IndexPageController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		List<User> users = userDao.findAll();
		ModelAndView model = new ModelAndView("index");
		model.addObject("userList", users);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
}
