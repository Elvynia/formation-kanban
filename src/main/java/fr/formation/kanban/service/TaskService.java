package fr.formation.kanban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.kanban.dao.TaskDao;
import fr.formation.kanban.model.Category;
import fr.formation.kanban.model.Task;

@Service
public class TaskService extends RestService<Task> {

	@Autowired
	private TaskDao dao;
	
	@Override
	protected JpaRepository<Task, Integer> getDao() {
		return this.dao;
	}

	public List<Task> readAll(Integer categoryId) {
		final Category category = new Category();
		category.setId(categoryId);
		final Task task = new Task();
		task.setCategory(category);
		return this.dao.findAll(Example.of(task));
	}

}
