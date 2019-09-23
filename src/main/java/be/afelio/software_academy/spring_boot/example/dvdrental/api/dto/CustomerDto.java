package be.afelio.software_academy.spring_boot.example.dvdrental.api.dto;

public class CustomerDto {
	
	private String firstname;
	private String name;
	private String email;
	private String address;
	private String city;
	private String country;
	private int store;
	
	public CustomerDto() {}

	public CustomerDto(String firstname, String name, String email, 
			String address, String city, String country, int store) {
		super();
		this.firstname = firstname;
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
		this.store = store;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getStore() {
		return store;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + store;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CustomerDto))
			return false;
		CustomerDto other = (CustomerDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (store != other.store)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerDto [firstname=" + firstname + ", name=" + name + ", email=" + email + ", address=" + address
				+ ", city=" + city + ", country=" + country + ", store=" + store + "]";
	}
	
}
