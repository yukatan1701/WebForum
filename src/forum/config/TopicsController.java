package forum.config;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.*;
import forum.enums.Permissions;

@Controller
public class TopicsController {
	@Autowired
	private TopicService topicService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/topics")
	public ModelAndView topics(@RequestParam("section_id") int id, @RequestParam(value="error_type", required=false) String error) {
		ModelAndView model = new ModelAndView("topics");
		if (error != null) {
			if (error.equals("bad_permissions")) {
				model.addObject("delete_topic_error", "Недостаточно прав");
			} else if (error.equals("existing_topic")) {
				model.addObject("add_topic_error", "Тема с таким именем уже существует");
			}
		}
		Section section = sectionService.findById(id);
		List<Topic> topics = new ArrayList<Topic>();
		section.getTopics().forEach(topic -> topics.add((Topic) topic));
		model.addObject("section", section);
		model.addObject("topicList", topics);
		//return new ModelAndView("index", "userList", users);
		return model;
	}
	
	@RequestMapping(value= "/topics/add_topic", method = RequestMethod.POST)
	public String addTopic(@RequestParam("section_id") int id, @RequestParam("title") String title) {
		Topic t = new Topic(sectionService.findById(id), title);
		try {
			this.topicService.addTopic(t);
		} catch (RuntimeException ex) {
			return "redirect:/topics?section_id=" + id + "&error_type=existing_topic";
		}
		return "redirect:/topics?section_id=" + String.valueOf(id);
	}
	
	@RequestMapping(value="/topics/delete_topic")
	public String deleteTopic(@RequestParam("section_id") int section_id, @RequestParam("topic_id") int topic_id,
			@RequestParam("login") String login) {
		User user = userService.findByLogin(login);
		if (user.getPermissions() == Permissions.USER) {
			return "redirect:/topics?section_id=" + section_id + "&error_type=bad_permissions";
		}
		topicService.deleteById(topic_id);
		return "redirect:/topics?section_id=" + section_id;       
	}
}
