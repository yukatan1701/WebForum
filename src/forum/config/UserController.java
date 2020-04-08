package forum.config;

import forum.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
@Controller
public class UserController {
	private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setPersonService(UserService us){
		this.userService = us;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.findAll());
		return "person";
	}
	
	@RequestMapping(value= "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User u){
		
		if (u.getUserId() == 0) {
			//new person, add it
			this.userService.addUser(u);
		} else {
			//existing person, call update
			this.userService.update(u);
		}
		
		return "redirect:/users";
	}
	
	@RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
		
        this.userService.deleteById(id);;
        return "redirect:/users";
    }
 
    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("users", this.userService.findById(id));
        model.addAttribute("listUsers", this.userService.findAll());
        return "user";
    }
}*/
