package fr.formation.kanban.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.formation.kanban.factory.QueryBuilder;
import fr.formation.kanban.model.Task;

public class TaskDao implements Dao<Task> {

	@Override
	public Task create(Task task) {
		final String query = QueryBuilder.newQuery()
				.insertInto("task", "title", "description", "points",
						"createdOn", "lastModifiedOn", "id_category")
				.values("'" + task.getTitle() + "'",
						"'" + task.getDescription() + "'",
						task.getPoints(),
						this.prepareDate(task.getCreatedOn()),
						this.prepareDate(task.getLastModifiedOn()),
						task.getIdCategory())
				.build();
		task.setId(prepareAndExecuteCreate(query));
		return task;
	}

	@Override
	public Task read(Task task) {
		final String query = QueryBuilder.newQuery().select("*").from("task")
				.where("id=" + task.getId()).build();
		try {
			final Statement stmt = this.getConnection().createStatement();
			final ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				task.setTitle(rs.getString("title"));
				task.setDescription(rs.getString("description"));
				task.setPoints(rs.getInt("points"));
				task.setCreatedOn(rs.getDate("createdOn"));
				task.setLastModifiedOn(rs.getDate("lastModifiedOn"));
				task.setIdCategory(rs.getInt("id_category"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

	@Override
	public Task update(Task task) {
		final String query = QueryBuilder.newQuery().update("task")
				.set("title='" + task.getTitle() + "'",
						"description='" + task.getDescription() + "'",
						"points=" + task.getPoints(),
						"createdOn=" + this.prepareDate(task.getCreatedOn()),
						"lastModifiedOn="
								+ this.prepareDate(task.getLastModifiedOn()),
						"id_category=" + task.getIdCategory())
				.where("id=" + task.getId()).build();
		if (prepareAndExecuteUpdate(query) == null) {
			return null;
		}
		return task;
	}

	@Override
	public Task delete(Task task) {
		final String query = QueryBuilder.newQuery().delete("task")
				.where("id=" + task.getId()).build();
		if (prepareAndExecuteUpdate(query) == null) {
			return null;
		}
		return task;
	}

}
