package fr.formation.kanban.factory;

public class QueryBuilder {

	public static QueryBuilder newQuery() {
		return new QueryBuilder();
	}

	private StringBuilder query;
	
	public QueryBuilder() {
		this.query = new StringBuilder();
	}
	
	public QueryBuilder delete(final String table) {
		query.append("DELETE FROM ");
		query.append(table);
		return this;
	}

	public QueryBuilder select(final String... args) {
		query.append("SELECT ");
		if (args != null) {
			query.append(args[0]);
			if (args.length > 1) {
				for (int i = 1; i < args.length; ++i) {
					query.append(',');
					query.append(args[i]);
				}
			}
		}
		return this;
	}
	
	public QueryBuilder from(final String table) {
		query.append(" FROM ");
		query.append(table);
		return this;
	}
	
	public QueryBuilder insertInto(final String table, final String... columns) {
		query.append("INSERT INTO ");
		query.append(table);
		if (columns != null) {
			query.append(" (");
			query.append(columns[0]);
			if (columns.length > 1) {
				for (int i = 1; i < columns.length; ++i) {
					query.append(',');
					query.append(columns[i]);
				}
			}
			query.append(')');
		}
		return this;
	}
	
	public QueryBuilder update(final String table) {
		query.append("UPDATE ");
		query.append(table);
		return this;
	}
	
	public QueryBuilder set(final String... args) {
		query.append(" SET ");
		if (args != null) {
			query.append(args[0]);
			if (args.length > 1) {
				for (int i = 1; i < args.length; ++i) {
					query.append(',');
					query.append(args[i]);
				}
			}
		}
		return this;
	}
	
	public QueryBuilder values(final Object... args) {
		query.append(" VALUES ");
		if (args != null) {
			query.append(" (");
			query.append(args[0]);
			if (args.length > 1) {
				for (int i = 1; i < args.length; ++i) {
					query.append(',');
					query.append(args[i]);
				}
			}
			query.append(')');
		}
		return this;
	}
	
	public QueryBuilder where(final String condition) {
		query.append(" WHERE ");
		query.append(condition);
		return this;
	}
	
	public String build() {
		return this.query.toString();
	}
}
