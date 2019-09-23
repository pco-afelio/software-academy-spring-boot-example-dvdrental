package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities;

import javax.persistence.*;

@Entity(name="Country")
@Table(name="country")
public class CountryEntity {
	
	@Id
	@Column(name="country_id") 
	private Integer id;
	
	@Column(name="country")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
