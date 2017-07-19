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

import fr.formation.kanban.model.Kanban;
import fr.formation.kanban.service.KanbanService;

@RestController
@RequestMapping("/api/kanban")
public class KanbanController {
	
	@Autowired
	private KanbanService service;

	@GetMapping({"", "/"})
	public List<Kanban> list() {
		return this.service.readAll();
	}
	
	@GetMapping(path={"/{id}"}, produces={"application/json"})
	public Kanban get(@PathVariable final Integer id) {
		return this.service.read(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Integer id) {
		this.service.delete(id);
	}
	
	@PutMapping(path="/", consumes={"application/json"})
	public Kanban create(@RequestBody final Kanban kanban) {
		return this.service.create(kanban);
	}
	
	@PostMapping(path="/", consumes={"application/json"})
	public Kanban update(@RequestBody final Kanban kanban) {
		return this.service.update(kanban);
	}
}
