package fr.formation.kanban.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.formation.kanban.model.Category;
import fr.formation.kanban.model.Kanban;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDaoTest {

	private static CategoryDao categoryDao;
	private static Category category;
	private static Kanban kanban;

	@BeforeClass
	public static void init() {
		// Préparation de l'instance unique (singleton) du DAO.
		CategoryDaoTest.categoryDao = new CategoryDao();
		// Préparation d'une instance de catégorie utilisée pour les tests.
		CategoryDaoTest.category = new Category();
		CategoryDaoTest.category.setName("Developments");
		CategoryDaoTest.category.setOrder(1);
		
		CategoryDaoTest.kanban = new Kanban();
		new KanbanDao().create(CategoryDaoTest.kanban);
		
		CategoryDaoTest.category.setIdKanban(CategoryDaoTest.kanban.getId());
	}
	
	@AfterClass
	public static void dispose() {
		new KanbanDao().delete(CategoryDaoTest.kanban);
	}

	@Test
	public void test1_create() {
		// Création de la nouvelle catégorie.
		CategoryDaoTest.categoryDao.create(CategoryDaoTest.category);
		// Après exécution, le champ 'id' de CategoryDaoTest.category est maintenant
		// rempli avec la valeur générée par l'auto-incrémentation de MySQL.
		Assert.assertNotNull(CategoryDaoTest.category.getId());
	}

	@Test
	public void test2_read() {
		// Construction d'une catégorie temporaire pour comparer avec
		// CategoryDaoTest.category.
		final Category cat = new Category();
		cat.setId(CategoryDaoTest.category.getId());
		// Lecture de la catégorie avec l'identifiant rempli.
		CategoryDaoTest.categoryDao.read(cat);
		// Comparaison des champs entre la catégorie temporaire qui vient d'être
		// lue en BDD et la catégorie mémorisée dans l'instance de notre test
		// unitaire.
		Assert.assertEquals(CategoryDaoTest.category.getName(), cat.getName());
		Assert.assertEquals(CategoryDaoTest.category.getOrder(), cat.getOrder());
		Assert.assertEquals(CategoryDaoTest.category.getIdKanban(), cat.getIdKanban());
	}

	@Test
	public void test3_update() {
		// Modifier les champs de la catégorie mémorisée dans l'instance.
		CategoryDaoTest.category.setName("Devs");
		CategoryDaoTest.category.setOrder(5);
		// Exécuter la mise à jour en BDD.
		CategoryDaoTest.categoryDao.update(CategoryDaoTest.category);
		// Vérifier à nouveau avec un read() que les informations en BDD sont
		// identiques aux informations dans CategoryDaoTest.category.
		this.test2_read();
	}

	@Test
	public void test4_delete() {
		// Suppression de la catégorie créée pendant le test.
		CategoryDaoTest.categoryDao.delete(CategoryDaoTest.category);
		// Vérification du retour de la méthode 
		Assert.assertNotNull(CategoryDaoTest.category);
	}
}
