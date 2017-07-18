package fr.formation.kanban.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.kanban.model.Kanban;

public interface KanbanDao extends JpaRepository<Kanban, Integer>{

}
