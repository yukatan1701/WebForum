package forum;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/")
	public ModelAndView home() {
		List<User> users = userDao.findAll();
		ModelAndView model = new ModelAndView("home");
		model.addObject("userList", users);
		return model;
	}
}
