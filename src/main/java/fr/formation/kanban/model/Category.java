package fr.formation.kanban.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private Integer id;
	
	private String name;
	
	private Integer order;

	private transient List<Task> tasks;
	
	private Integer idKanban;
	
	public Category() {
		this.tasks = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Integer getIdKanban() {
		return idKanban;
	}

	public void setIdKanban(Integer idKanban) {
		this.idKanban = idKanban;
	}
}
