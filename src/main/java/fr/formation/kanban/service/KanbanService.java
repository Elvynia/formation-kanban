package fr.formation.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.kanban.dao.KanbanDao;
import fr.formation.kanban.model.Kanban;

@Service
public class KanbanService extends RestService<Kanban> {

	@Autowired
	private KanbanDao dao;

	@Override
	protected JpaRepository<Kanban, Integer> getDao() {
		return this.dao;
	}
	
	
}
