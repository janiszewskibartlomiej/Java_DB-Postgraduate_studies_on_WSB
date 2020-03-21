import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionTest {

	@Test
	void connectionTest() throws SQLException {
		try (
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/sakila?serverTimezone=UTC", "root", "qwerty");
			Statement statement = connection.createStatement()
		) {
			ResultSet result = statement.executeQuery("SELECT COUNT(*) AS c FROM film");
			result.first();
			int filmsCount = result.getInt("c");
			assertEquals(1000, filmsCount);

			System.out.println("Połączenie OK!");
		}
	}

}
