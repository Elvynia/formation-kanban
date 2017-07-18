package fr.formation.kanban.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.kanban.model.Task;

public interface TaskDao extends JpaRepository<Task, Integer>{

}
