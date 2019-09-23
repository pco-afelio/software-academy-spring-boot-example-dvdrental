package be.afelio.software_academy.spring_boot.example.dvdrental.api.dto;

public class UpdateCustomerEmailDto {

	private String firstname;
	private String name;
	private String email;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UpdateCustomerEmailDto [firstname=" + firstname + ", name=" + name + ", email=" + email + "]";
	}
	
}
