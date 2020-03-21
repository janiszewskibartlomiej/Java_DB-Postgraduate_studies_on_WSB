package jdbc.domain;

public class JdbcFilm {

	private Integer filmId;
	private String title;
	private Double rentalRate;
	private Integer rentalDuration;

	public JdbcFilm(Integer filmId, String title, Double rentalRate, Integer rentalDuration) {
		this.filmId = filmId;
		this.title = title;
		this.rentalRate = rentalRate;
		this.rentalDuration = rentalDuration;
	}

	@Override
	public String toString() {
		return "Film [" + filmId + "]: " + title + " ($" + rentalRate + " per " + rentalDuration + " days)";
	}

	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
}
