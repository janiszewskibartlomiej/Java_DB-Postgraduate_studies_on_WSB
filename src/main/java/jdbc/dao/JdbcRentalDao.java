package jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcRentalDao extends JdbcDao {

	public JdbcRentalDao(Connection connection) throws SQLException {
		super(connection);
	}

	public int countByDay(LocalDate day) throws SQLException {
		ResultSet result =  statement.executeQuery(
				"SELECT COUNT(*) as rental_count, DATE(rental_date) as rental_day FROM sakila.rental " +
						"group by rental_day " +
						"having rental_day = '" + day + "'");

		boolean exists = result.first();

		if (exists) {
			int count = result.getInt("rental_count");
			return count;
		} else {
			return 0;
		}

	}

}
