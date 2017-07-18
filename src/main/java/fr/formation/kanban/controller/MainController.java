package fr.formation.kanban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.kanban.dao.TaskDao;

@Controller
public class MainController {
	
	@Autowired
	private TaskDao taskDao;

	@RequestMapping("/")
	public String index(final Model model) {
		model.addAttribute("tasks", this.taskDao.findAll());
		return "index";
	}
}
