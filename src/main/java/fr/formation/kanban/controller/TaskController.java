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

import fr.formation.kanban.model.Task;
import fr.formation.kanban.service.TaskService;

@RestController
@RequestMapping("/api/kanban/{kanbanId}/category/{categoryId}/task")
public class TaskController {

	@Autowired
	private TaskService service;
	
	@RequestMapping({"", "/"})
	public List<Task> list(@PathVariable final Integer categoryId) {
		return this.service.readAll(categoryId);
	}
	
	@GetMapping(path="/{id}", produces={"application/json"})
	public Task get(@PathVariable final Integer id) {
		return this.service.read(id);
	}
	
	@PutMapping(path={"", "/"}, consumes={"application/json"})
	public Task create(@RequestBody final Task task) {
		return this.service.create(task);
	}
	
	@PostMapping(path={"", "/"}, consumes={"application/json"})
	public Task update(@RequestBody final Task task) {
		return this.service.update(task);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Integer id) {
		this.service.delete(id);
	}
}
