package fr.formation.kanban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.kanban.dao.CategoryDao;
import fr.formation.kanban.model.Category;
import fr.formation.kanban.model.Kanban;

@Service
public class CategoryService extends RestService<Category> {

	@Autowired
	private CategoryDao dao;
	
	@Override
	protected JpaRepository<Category, Integer> getDao() {
		return this.dao;
	}
	
	public List<Category> readAll(final Integer id) {
		final Kanban kanban = new Kanban();
		kanban.setId(id);
		final Category category = new Category();
		category.setKanban(kanban);
		return this.dao.findAll(Example.of(category));
	}

}
