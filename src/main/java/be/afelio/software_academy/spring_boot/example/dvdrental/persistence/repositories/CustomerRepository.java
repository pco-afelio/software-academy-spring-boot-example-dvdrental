package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.CustomerDto;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	
	@Query("select new be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.CustomerDto("
			+ "	c.firstname, c.name, c.email, c.address.value, c.address.city.name, c.address.city.country.name,"
			+ " c.store.id)"
			+ " from Customer c where lower(c.firstname) = lower(?1) and lower(c.name) = lower(?2)")
	CustomerDto findOneByFirstnameAndNameAsDto(String firstname, String name);
	
	CustomerEntity findOneByFirstnameIgnoreCaseAndNameIgnoreCase(String firstname, String name);
	CustomerEntity findOneByEmail(String email);
}
