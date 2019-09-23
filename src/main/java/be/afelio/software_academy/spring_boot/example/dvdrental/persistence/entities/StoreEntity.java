package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities;

import javax.persistence.*;

@Entity(name="Store")
@Table(name="store")
public class StoreEntity {

	@Id
	@Column(name="store_id") 
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private AddressEntity address;
	
	public Integer getId() {
		return id;
	}
	
	public AddressEntity getAddress() {
		return address;
	}
}
