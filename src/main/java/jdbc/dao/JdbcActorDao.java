package jdbc.dao;

import jdbc.domain.JdbcActor;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcActorDao extends JdbcDao {

	public JdbcActorDao(Connection connection) throws SQLException {
		super(connection);
	}

	public JdbcActor findById(int searchId) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void persist(JdbcActor actor) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
