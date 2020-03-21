package jdbc.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcStoreDaoTest {

	static Connection connection;
	static JdbcStoreDao jdbcStoreDao;
	static Auth pass = new Auth();

	@BeforeAll
	static void beforeAll() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/sakila?serverTimezone=UTC", "root", pass.getPassword());

		jdbcStoreDao = new JdbcStoreDao(connection);
	}

	@AfterAll
	static void afterAll() throws SQLException {
		connection.close();
	}

	@Test
	void list() throws SQLException {
		List<String> stores = jdbcStoreDao.list();

		stores.forEach(System.out::println);

		assertEquals(2, stores.size());
		assertEquals("Store id: 1, Address id: 1", stores.get(0));
	}

	@Test
	void listWithAddresses() throws SQLException {
		List<String> stores = jdbcStoreDao.listWithAddresses();

		stores.forEach(System.out::println);

		assertEquals(2, stores.size());
		assertEquals("Store id: 1, Address: 47 MySakila Drive, Alberta", stores.get(0));
	}

	@Test
	void listWithFullAddresses() throws SQLException {
		List<String> stores = jdbcStoreDao.listWithFullAddresses();

		stores.forEach(System.out::println);

		assertEquals(2, stores.size());
		assertEquals("Store id: 1, Address: 47 MySakila Drive, Alberta, Lethbridge, Canada", stores.get(0));

	}
}
