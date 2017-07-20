package fr.formation.kanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.kanban.model.Category;
import fr.formation.kanban.service.CategoryService;

@RestController
@RequestMapping("/api/kanban/{kanbanId}/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	@RequestMapping({"", "/"})
	public List<Category> list(@PathVariable final Integer kanbanId) {
		return this.service.readAll(kanbanId);
	}
	
	@GetMapping(path={"/{id}"}, produces={"application/json"})
	public Category get(@PathVariable final Integer id) {
		return this.service.read(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Integer id) {
		this.service.delete(id);
	}
	
	@PutMapping(path={"", "/"}, consumes={"application/json"})
	public Category create(@RequestBody final Category category) {
		return this.service.create(category);
	}
	
	@PostMapping(path={"", "/"}, consumes={"application/json"})
	public Category update(@RequestBody final Category category) {
		return this.service.update(category);
	}
}
