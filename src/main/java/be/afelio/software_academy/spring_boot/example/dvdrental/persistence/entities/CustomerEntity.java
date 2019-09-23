package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities;

import javax.persistence.*;

@Entity(name="Customer")
@Table(name="customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id") 
	private Integer id;

	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String name;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private AddressEntity address;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private StoreEntity store;
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public StoreEntity getStore() {
		return store;
	}

	public void setStore(StoreEntity store) {
		this.store = store;
	}
	
}
