package fr.formation.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.kanban.dao.TaskDao;
import fr.formation.kanban.model.Task;

@Service
public class TaskService extends RestService<Task> {

	@Autowired
	private TaskDao dao;
	
	@Override
	protected JpaRepository<Task, Integer> getDao() {
		return this.dao;
	}

}
