package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities;

import javax.persistence.*;

@Entity(name="City")
@Table(name="city")
public class CityEntity {

	@Id
	@Column(name="city_id")
	private Integer id;
	
	@Column(name="city")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountryEntity country;

	public String getName() {
		return name;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}
}
