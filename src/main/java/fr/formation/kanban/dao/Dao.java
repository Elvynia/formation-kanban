package fr.formation.kanban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.formation.kanban.factory.SQLConnectionFactory;

/**
 * Définition d'un contrat CRUD commun à tous les DAO.
 * 
 * @param <ENTITY>
 * entité métier gérée par le DAO.
 */
public interface Dao<ENTITY> {
	
	static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Création d'une entité en BDD.
	 * 
	 * @param entity
	 * avec chhamp id vide.
	 * @return ENTITY avec champ id rempli depuis la réponse de la BDD.
	 */
	public ENTITY create(ENTITY entity);

	/**
	 * Récupérer les informations d'une entité.
	 * 
	 * @param entity
	 * l'entité avec le champ id permettant de la retrouver en BDD.
	 * @return ENTITY l'entité avec toutes les informations provenant de la BDD.
	 */
	public ENTITY read(ENTITY entity);

	/**
	 * Mise à jour des informations d'une entité.
	 * 
	 * @param entity
	 * l'entité avec identifiant existant déjà en BDD.
	 * @return ENTITY l'entité si la mise à jour est effectuée avec succès,
	 * sinon null.
	 */
	public ENTITY update(ENTITY entity);

	/**
	 * Suppression d'une entité de la BDD.
	 * 
	 * @param entity
	 * l'entité avec champ id rempli.
	 * @return ENTITY l'entité si la suppression s'est passée correctement sinon
	 * null.
	 */
	public ENTITY delete(ENTITY entity);

	/**
	 * @return Connection une référence à la connexion JDBC par accès à la
	 * fabrique correspondante.
	 */
	default Connection getConnection() {
		return SQLConnectionFactory.getInstance();
	}
	
	default Integer prepareAndExecuteCreate(final String query) {
		Integer newId = null;
		try {
			final PreparedStatement stmt = this.getConnection().prepareStatement(
					query, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			final ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			newId = rs.getInt("GENERATED_KEY");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newId;
	}
	
	default Integer prepareAndExecuteUpdate(final String query) {
		Integer count = null;
		try {
			final Statement stmt = this.getConnection().createStatement();
			count = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	default String prepareDate(Date date) {
		return "'".concat(Dao.formatter.format(date)).concat("'");
	}
}
