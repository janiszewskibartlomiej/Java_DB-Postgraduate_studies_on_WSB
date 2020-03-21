package jdbc.dao;

import jdbc.domain.JdbcActor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JdbcActorDaoTest {

	static Connection connection;
	static JdbcActorDao jdbcActorDao;
	static Auth pass = new Auth();
	@BeforeAll
	static void beforeAll() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/sakila?serverTimezone=UTC", "root", pass.getPassword());
		connection.setAutoCommit(true);

		jdbcActorDao = new JdbcActorDao(connection);
	}

	@AfterAll
	static void afterAll() throws SQLException {
		connection.close();
	}

	@Test
	void persist() throws SQLException {
		JdbcActor tomHanks = new JdbcActor(1000, "TOM", "HANKS");
		jdbcActorDao.persist(tomHanks);

		JdbcActor actor = jdbcActorDao.findById(1000);
		assertNotNull(actor);
	}

}
