package jdbc.dao;

import jdbc.domain.JdbcActor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcActorDao extends JdbcDao {

	public JdbcActorDao(Connection connection) throws SQLException {
		super(connection);
	}

	public JdbcActor findById(int searchId) throws SQLException {
		ResultSet actorResult = statement.executeQuery("SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = " + searchId);

		boolean filmExists = actorResult.first();
		if (filmExists) {
			int actorId = actorResult.getInt("actor_id");
			String firstName = actorResult.getString("first_name");
			String lastName = actorResult.getString("last_name");
			return new JdbcActor(actorId, firstName, lastName);
		} else {
			return null;
		}

	}

	public void persist(JdbcActor actor) throws SQLException {
		statement.executeUpdate(
				"INSERT IGNORE INTO actor " +
						"(actor_id, first_name, last_name) " +
						"VALUES " +
						"(" + actor.getActorId() + ", '" + actor.getFirstName() + "', '" + actor.getLastName() + "')");

	}

}
