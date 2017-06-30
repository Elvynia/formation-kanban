package fr.formation.kanban.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionFactory {

	private static Connection instance;

	public static Connection getInstance() {
		if (SQLConnectionFactory.instance == null) {
			try {
				SQLConnectionFactory.instance = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/kanban?useSSL=false",
						"root", "root");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return SQLConnectionFactory.instance;
	}
}
