package orm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Film {

	@Id
	private Integer filmId;

	private String title;

	private Double rentalRate;

	private Integer rentalDuration;

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

	@Override
	public String toString() {
		return "Film [" + filmId + "]: " + title + " ($" + rentalRate + " per " + rentalDuration + " days)";
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
