package jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JdbcDao {

	protected final Connection connection;
	protected final Statement statement;

	public JdbcDao(Connection connection) throws SQLException {
		this.connection = connection;
		this.statement = connection.createStatement();
	}

}
