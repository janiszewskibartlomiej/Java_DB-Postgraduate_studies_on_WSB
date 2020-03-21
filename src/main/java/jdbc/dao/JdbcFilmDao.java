package jdbc.dao;

import jdbc.domain.JdbcFilm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcFilmDao extends JdbcDao {

	private PreparedStatement findById;

	public JdbcFilmDao(Connection connection) throws SQLException {
		super(connection);

		findById = connection.prepareStatement(
					"SELECT film_id, title, rental_duration, rental_rate " +
						"FROM film " +
						"WHERE film_id = ?");
	}

	public JdbcFilm findById(int searchId) throws SQLException {
		ResultSet resut = statement.executeQuery(
				"SELECT film_id, title, rental_duration, rental_rate " +
						"FROM film " +
						"WHERE film_id = " + searchId
		);
		boolean filmExists = resut.first();

		if (filmExists) {
			int filmId = resut.getInt("film_id");
			String title = resut.getNString("title");
			int duration = resut.getInt("rental_duration");
			double rate = resut.getDouble("rental_rate");

			JdbcFilm film = new JdbcFilm(filmId, title, rate, duration);

			return film;
		} else {
			return null;
		}


	}

	public JdbcFilm findByIdWithPreparedStatement(int searchId) throws SQLException {
		findById.setInt(1,searchId);

		ResultSet resut = findById.executeQuery();

		boolean filmExists = resut.first();

		if (filmExists) {
			int filmId = resut.getInt("film_id");
			String title = resut.getNString("title");
			int duration = resut.getInt("rental_duration");
			double rate = resut.getDouble("rental_rate");

			JdbcFilm film = new JdbcFilm(filmId, title, rate, duration);

			return film;
		} else {
			return null;
		}
	}

}
