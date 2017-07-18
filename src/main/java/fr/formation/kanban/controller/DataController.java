package fr.formation.kanban.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.kanban.service.DataService;

@RestController
@RequestMapping("/api")
public class DataController {
	
	@Autowired
	private DataService dataService;
	
	@GetMapping({"", "/"})
	// @ResponseBody (déjà ajouté par la présence de l'annotation RestController).
	public Map<Integer, Date> list() {
		return this.dataService.listKanban();
	}
}
