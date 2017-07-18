package fr.formation.kanban.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.kanban.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
