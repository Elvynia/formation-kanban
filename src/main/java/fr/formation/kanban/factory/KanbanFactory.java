package fr.formation.kanban.factory;

import java.util.ArrayList;
import java.util.Date;

import fr.formation.kanban.model.Category;
import fr.formation.kanban.model.Kanban;
import fr.formation.kanban.model.Task;

public class KanbanFactory {

	private static Kanban instance;
	private static int idCount = 0;

	public static Kanban getInstance() {
		if (KanbanFactory.instance == null) {
			KanbanFactory.instance = new Kanban();
			KanbanFactory.instance.setId(++idCount);
			KanbanFactory.instance.setOpenedOn(new Date());
			KanbanFactory.generateCategory("Analyse");
			KanbanFactory.generateTask("Demande client #378",
					"Le client veut que l'application remonte des alertes par mail.");
			KanbanFactory.generateCategory("Conception");
			KanbanFactory.generateTask("Ajout d'un champ de saisie",
					"Il faut un nouveau champ de type texte dans l'entité facture.");
			KanbanFactory.generateCategory("Developments");
			KanbanFactory.generateTask("Bug #1024", "Ca marche pas !!!!");
			KanbanFactory.generateTask("Bug #1025",
					"Le client n'est pas content...");
			KanbanFactory.generateCategory("Validation");
			KanbanFactory.generateTask("Regression #27",
					"Vérifier si la facturation fonctionne en mode manager.");
		}
		return KanbanFactory.instance;
	}

	private static void generateCategory(String name) {
		final Category cat = new Category();
		cat.setId(++idCount);
		cat.setName(name);
		cat.setTasks(new ArrayList<>());
		KanbanFactory.instance.getCategories().add(cat);
	}

	private static void generateTask(String title, String description) {
		final Category cat = KanbanFactory.instance.getCategories()
				.get(KanbanFactory.instance.getCategories().size() - 1);
		final Task task = new Task();
		task.setId(++idCount);
		task.setTitle(title);
		task.setDescription(description);
		task.setCreatedOn(new Date());
		task.setLastModifiedOn(task.getCreatedOn());
		task.setPoints(3);
		cat.getTasks().add(task);
	}
}
