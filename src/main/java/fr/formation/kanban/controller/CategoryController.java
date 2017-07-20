package fr.formation.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.kanban.model.Category;
import fr.formation.kanban.service.CategoryService;

@RestController
@RequestMapping("/api/kanban/{id}/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	@RequestMapping({"", "/"})
	public List<Category> list(@PathVariable final Integer id) {
		return this.service.readAll();
	}
}
