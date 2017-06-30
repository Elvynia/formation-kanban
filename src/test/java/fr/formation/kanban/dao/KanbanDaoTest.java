package fr.formation.kanban.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.formation.kanban.model.Kanban;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KanbanDaoTest {

	private static KanbanDao kanbanDao;
	private static Kanban kanban;
	private static DateFormat formatter;

	@BeforeClass
	public static void init() {
		KanbanDaoTest.formatter = new SimpleDateFormat("yyyy-MM-dd");
		// Préparation de l'instance unique (singleton) du DAO.
		KanbanDaoTest.kanbanDao = new KanbanDao();
		// Préparation d'une instance de catégorie utilisée pour les tests.
		KanbanDaoTest.kanban = new Kanban();
		KanbanDaoTest.kanban.setOpenedOn(new Date());
		KanbanDaoTest.kanban.setClosedOn(new Date());
	}

	@Test
	public void test1_create() {
		// Création de la nouvelle catégorie.
		KanbanDaoTest.kanbanDao.create(KanbanDaoTest.kanban);
		// Après exécution, le champ 'id' de KanbanDaoTest.kanban est maintenant
		// rempli avec la valeur générée par l'auto-incrémentation de MySQL.
		Assert.assertNotNull(KanbanDaoTest.kanban.getId());
	}

	@Test
	public void test2_read() {
		// Construction d'une catégorie temporaire pour comparer avec
		// KanbanDaoTest.kanban.
		final Kanban cat = new Kanban();
		cat.setId(KanbanDaoTest.kanban.getId());
		// Lecture de la catégorie avec l'identifiant rempli.
		KanbanDaoTest.kanbanDao.read(cat);
		// Comparaison des champs entre la catégorie temporaire qui vient d'être
		// lue en BDD et la catégorie mémorisée dans l'instance de notre test
		// unitaire.
		Assert.assertEquals(
				KanbanDaoTest.formatter
						.format(KanbanDaoTest.kanban.getOpenedOn()),
				KanbanDaoTest.formatter.format(cat.getOpenedOn()));
		Assert.assertEquals(
				KanbanDaoTest.formatter
						.format(KanbanDaoTest.kanban.getClosedOn()),
				KanbanDaoTest.formatter.format(cat.getClosedOn()));
	}

	@Test
	public void test3_update() {
		// Modifier les champs de la catégorie mémorisée dans l'instance.
		KanbanDaoTest.kanban.setOpenedOn(new Date());
		KanbanDaoTest.kanban.setClosedOn(new Date());
		// Exécuter la mise à jour en BDD.
		KanbanDaoTest.kanbanDao.update(KanbanDaoTest.kanban);
		// Vérifier à nouveau avec un read() que les informations en BDD sont
		// identiques aux informations dans KanbanDaoTest.kanban.
		this.test2_read();
	}

	@Test
	public void test4_delete() {
		// Suppression de la catégorie créée pendant le test.
		KanbanDaoTest.kanbanDao.delete(KanbanDaoTest.kanban);
		// Vérification du retour de la méthode
		Assert.assertNotNull(KanbanDaoTest.kanban);
	}
}
