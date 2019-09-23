package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities;

import javax.persistence.*;

@Entity(name="Address")
@Table(name="address")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id") 
	private Integer id;
	
	@Column(name="address")
	private String value;
	
	@SuppressWarnings("unused")
	private String district = "";
	@SuppressWarnings("unused")
	private String phone = "";
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private CityEntity city;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public CityEntity getCity() {
		return city;
	}
	
	public void setCity(CityEntity city) {
		this.city = city;
	}

}
