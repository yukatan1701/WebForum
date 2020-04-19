package forum.config;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.Section;
import forum.Topic;
import forum.User;
import forum.enums.*;
import forum.UserService;

@Controller
public class UserPageController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/my_page")
	public String user(@RequestParam("login") String login) {
		User user = userService.findByLogin(login);
		int id = user.getUserId();
		return "redirect:/user?id=" + id;
	}
	
	@RequestMapping(value = "/user")
	public ModelAndView user(@RequestParam("id") int id, @RequestParam(value="error_type", required=false) String error) {
		ModelAndView model = new ModelAndView("user");
		if (error != null) {
			model.addObject("block_user_error", "Недостаточно прав");
		}
		User user = userService.findById(id);
		model.addObject("user", user);
		String permissions = user.getPermissions() == Permissions.USER ? "обычный пользователь" : "модератор";
		model.addObject("permissions", permissions);
		String status = user.getStatus() == Status.NORMAL ? "нормальный" : "заблокирован";
		model.addObject("status", status);
		String blockButtonText = user.getStatus() == Status.NORMAL ? "Заблокировать" : "Разблокировать";
		model.addObject("blockButtonText", blockButtonText);
		return model;
	}
	
	@RequestMapping(value= "/user/block", method = RequestMethod.POST)
	public String blockUser(@RequestParam("id") int id, @RequestParam("login") String login) {
		User currentUser = userService.findByLogin(login);
		if (currentUser.getPermissions() == Permissions.USER || currentUser.getLogin().equals("admin")) {
			return "redirect:/user?id=" + id + "&error_type=bad_permissions";
		}
		User user = userService.findById(id);
		if (user.getStatus() == Status.NORMAL) {
			userService.blockUser(user);
		} else {
			userService.unblockUser(user);
		}
		return "redirect:/user?id=" + id;
	}
}
