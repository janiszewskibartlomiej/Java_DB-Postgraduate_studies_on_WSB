package jdbc.dao;

import jdbc.domain.JdbcFilm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JdbcFilmDaoTest {

	static Connection connection;
	static JdbcFilmDao jdbcFilmDao;
	static Auth pass = new Auth();

	@BeforeAll
	static void beforeAll() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/sakila?serverTimezone=UTC", "root", pass.getPassword());

		jdbcFilmDao = new JdbcFilmDao(connection);
	}

	@AfterAll
	static void afterAll() throws SQLException {
		connection.close();
	}

	@Test
	void findById() throws SQLException {
		JdbcFilm film = jdbcFilmDao.findById(1);

		System.out.println(film);

		assertEquals(1, film.getFilmId());
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
		assertEquals(0.99, film.getRentalRate());
		assertEquals(6, film.getRentalDuration());
	}

	@Test
	void findById_nonExistent() throws SQLException {
		JdbcFilm film = jdbcFilmDao.findById(10_000);

		System.out.println(film);

		assertNull(film);
	}

	@Test
	void findByIdWithPreparedStatement() throws SQLException {
		JdbcFilm film = jdbcFilmDao.findByIdWithPreparedStatement(2);

		System.out.println(film);

		assertEquals(2, film.getFilmId());
		assertEquals("ACE GOLDFINGER", film.getTitle());
		assertEquals(4.99, film.getRentalRate());
		assertEquals(3, film.getRentalDuration());
	}
}
