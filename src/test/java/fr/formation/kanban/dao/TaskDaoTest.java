package fr.formation.kanban.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.formation.kanban.model.Category;
import fr.formation.kanban.model.Kanban;
import fr.formation.kanban.model.Task;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskDaoTest {

	private static TaskDao taskDao;
	private static Task task;
	private static Kanban kanban;
	private static Category category;

	@BeforeClass
	public static void init() {
		// Préparation de l'instance unique (singleton) du DAO.
		TaskDaoTest.taskDao = new TaskDao();
		// Préparation d'une instance de catégorie utilisée pour les tests.
		TaskDaoTest.task = new Task();
		TaskDaoTest.task.setIdCategory(1);
		TaskDaoTest.task.setTitle("Dev #001");
		TaskDaoTest.task.setDescription("Créer le POM Maven.");
		TaskDaoTest.task.setPoints(3);

		// Préparation des objets Kanban et Category liés avec Task.
		TaskDaoTest.kanban = new Kanban();
		new KanbanDao().create(TaskDaoTest.kanban);
		TaskDaoTest.category = new Category();
		TaskDaoTest.category.setOrder(1);
		// Lien entre la catégorie et son kanban.
		TaskDaoTest.category.setIdKanban(TaskDaoTest.kanban.getId());
		new CategoryDao().create(TaskDaoTest.category);
		
		// Lien entre la tâche et sa catégorie.
		TaskDaoTest.task.setIdCategory(TaskDaoTest.category.getId());
	}
	
	@AfterClass
	public static void dispose() {
		new CategoryDao().delete(TaskDaoTest.category);
		new KanbanDao().delete(kanban);
	}

	@Test
	public void test1_create() {
		// Création de la nouvelle catégorie.
		TaskDaoTest.taskDao.create(TaskDaoTest.task);
		// Après exécution, le champ 'id' de CategoryDaoTest.category est maintenant
		// rempli avec la valeur générée par l'auto-incrémentation de MySQL.
		Assert.assertNotNull(TaskDaoTest.task.getId());
	}

	@Test
	public void test2_read() {
		// Construction d'une catégorie temporaire pour comparer avec
		// CategoryDaoTest.category.
		final Task task = new Task();
		task.setId(TaskDaoTest.task.getId());
		// Lecture de la catégorie avec l'identifiant rempli.
		TaskDaoTest.taskDao.read(task);
		// Comparaison des champs entre la catégorie temporaire qui vient d'être
		// lue en BDD et la catégorie mémorisée dans l'instance de notre test
		// unitaire.
		Assert.assertEquals(TaskDaoTest.task.getTitle(), task.getTitle());
		Assert.assertEquals(TaskDaoTest.task.getDescription(), task.getDescription());
		Assert.assertEquals(TaskDaoTest.task.getPoints(), task.getPoints());
		Assert.assertEquals(TaskDaoTest.task.getIdCategory(), task.getIdCategory());
	}

	@Test
	public void test3_update() {
		// Modifier les champs de la catégorie mémorisée dans l'instance.
		TaskDaoTest.task.setTitle("Dev #001-bis");
		TaskDaoTest.task.setPoints(5);
		// Exécuter la mise à jour en BDD.
		TaskDaoTest.taskDao.update(TaskDaoTest.task);
		// Vérifier à nouveau avec un read() que les informations en BDD sont
		// identiques aux informations dans CategoryDaoTest.category.
		this.test2_read();
	}

	@Test
	public void test4_delete() {
		// Suppression de la catégorie créée pendant le test.
		TaskDaoTest.taskDao.delete(TaskDaoTest.task);
		// Vérification du retour de la méthode 
		Assert.assertNotNull(TaskDaoTest.task);
	}
}
