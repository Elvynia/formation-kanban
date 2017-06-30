package fr.formation.kanban.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.formation.kanban.factory.QueryBuilder;
import fr.formation.kanban.model.Kanban;

public class KanbanDao implements Dao<Kanban> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Kanban create(Kanban kanban) {
		final String query = QueryBuilder.newQuery()
				.insertInto("kanban", "openedOn", "closedOn")
				.values(this.prepareDate(kanban.getOpenedOn()),
						this.prepareDate(kanban.getClosedOn()))
				.build();
		kanban.setId(prepareAndExecuteCreate(query));
		return kanban;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Kanban read(final Kanban kanban) {
		final String query = QueryBuilder.newQuery().select("*").from("kanban")
				.where("id=" + kanban.getId()).build();
		try {
			final Statement stmt = this.getConnection().createStatement();
			final ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				kanban.setOpenedOn(rs.getDate("openedOn"));
				kanban.setClosedOn(rs.getDate("closedOn"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kanban;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Kanban update(Kanban kanban) {
		final String query = QueryBuilder.newQuery().update("kanban")
				.set("openedOn=" + this.prepareDate(kanban.getOpenedOn()),
						"closedOn=" + this.prepareDate(kanban.getClosedOn()))
				.where("id=" + kanban.getId()).build();
		if (prepareAndExecuteUpdate(query) == null) {
			return null;
		}
		return kanban;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Kanban delete(Kanban kanban) {
		final String query = QueryBuilder.newQuery().delete("kanban")
				.where("id=" + kanban.getId()).build();
		if (prepareAndExecuteUpdate(query) == null) {
			return null;
		}
		return kanban;
	}
	
}
