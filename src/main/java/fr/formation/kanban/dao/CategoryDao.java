package fr.formation.kanban.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.formation.kanban.factory.QueryBuilder;
import fr.formation.kanban.model.Category;

public class CategoryDao implements Dao<Category> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category create(final Category category) {
		final String query = QueryBuilder.newQuery()
				.insertInto("category", "name", "zorder", "id_kanban")
				.values("'" + category.getName() + "'",
						category.getOrder(),
						category.getIdKanban())
				.build();
		category.setId(prepareAndExecuteCreate(query));
		return category;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category read(final Category category) {
		final String query = QueryBuilder.newQuery().select("*")
				.from("category").where("id=" + category.getId()).build();
		try {
			final Statement stmt = this.getConnection().createStatement();
			final ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				category.setName(rs.getString("name"));
				category.setOrder(rs.getInt("zorder"));
				category.setIdKanban(rs.getInt("id_kanban"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category update(final Category category) {
		final String query = QueryBuilder.newQuery().update("category")
				.set("name='" + category.getName() + "'",
						"zorder=" + category.getOrder(),
						"id_kanban=" + category.getIdKanban())
				.where("id=" + category.getId()).build();
		if (prepareAndExecuteUpdate(query) == null) {
			return null;
		}
		return category;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category delete(final Category category) {
		final String query = QueryBuilder.newQuery().delete("category")
				.where("id=" + category.getId()).build();
		if (prepareAndExecuteUpdate(query) == null) {
			return null;
		}
		return category;
	}
}
