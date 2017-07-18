package fr.formation.kanban.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.kanban.dao.KanbanDao;
import fr.formation.kanban.model.Kanban;

@Service
public class DataService {

	@Autowired
	private KanbanDao kanbanDao;

	/**
	 * Recherche tous kanban en base de données et construit une map des dates
	 * d'ouvertures par identifiant.
	 * 
	 * @return Map<Integer, Date> toutes les dates d'ouverture de kanban par identifiant.
	 */
	public Map<Integer, Date> listKanban() {
		final Map<Integer, Date> kList = new HashMap<>();
		for (final Kanban kanban : this.kanbanDao.findAll()) {
			kList.put(kanban.getId(), kanban.getOpenedOn());
		}
		return kList;
	}
}
