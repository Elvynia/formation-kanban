package fr.formation.kanban.service;

import java.util.List;

import fr.formation.kanban.dao.CategoryDao;
import fr.formation.kanban.dao.KanbanDao;
import fr.formation.kanban.dao.TaskDao;
import fr.formation.kanban.model.Category;
import fr.formation.kanban.model.Kanban;

public class KanbanService {

	private KanbanDao kanbanDao;
	private CategoryDao categoryDao;
	private TaskDao taskDao;

	public KanbanService() {
		this.kanbanDao = new KanbanDao();
		this.categoryDao = new CategoryDao();
		this.taskDao = new TaskDao();
	}

	public Kanban findById(final Integer id) {
		final Kanban kanban = new Kanban();
		kanban.setId(id);
		// Récupérer les informations en BDD pour Kanban.
		this.kanbanDao.read(kanban);
		final List<Category> categories = this.categoryDao.findByIdKanban(id);
		for (final Category category : categories) {
			// Récupérer les tâches associées.
			category.getTasks().addAll(this.taskDao.findByIdCategory(category.getId()));
		}
		kanban.getCategories().addAll(categories);
		return kanban;
	}
}
