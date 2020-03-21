package jdbc.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcRentalDaoTest {

	static Connection connection;
	static JdbcRentalDao jdbcRentalDao;
	static Auth passs = new Auth();

	@BeforeAll
	static void beforeAll() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/sakila?serverTimezone=UTC", "root",passs.getPassword());

		jdbcRentalDao = new JdbcRentalDao(connection);
	}

	@AfterAll
	static void afterAll() throws SQLException {
		connection.close();
	}

	@Test
	void countByDay() throws SQLException {
		LocalDate day = LocalDate.of(2005, 5, 30);
		int rentalsCount = jdbcRentalDao.countByDay(day);

		System.out.println("Rentals on " + day + ": " + rentalsCount);

		assertEquals(158, rentalsCount);
	}

	@Test
	void countByDay_noResults() throws SQLException {
		LocalDate day = LocalDate.of(2010, 1, 1);
		int rentalsCount = jdbcRentalDao.countByDay(day);

		System.out.println("Rentals on " + day + ": " + rentalsCount);

		assertEquals(0, rentalsCount);
	}
}
