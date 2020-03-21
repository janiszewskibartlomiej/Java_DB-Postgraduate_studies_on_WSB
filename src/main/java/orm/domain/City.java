package orm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	private Integer cityId;

	private String city;

	@Override
	public String toString() {
		return "City [" + cityId + "] " + city;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
