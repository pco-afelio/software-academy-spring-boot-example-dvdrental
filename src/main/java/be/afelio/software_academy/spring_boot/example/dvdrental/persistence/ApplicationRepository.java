package be.afelio.software_academy.spring_boot.example.dvdrental.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.CustomerDto;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.CityNotFoundException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.CustomerNotFoundException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.DuplicatedCustomerException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.DuplicatedEmailException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.InvalidCreateParametersException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.StoreNotFoundException;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities.AddressEntity;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities.CityEntity;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities.CustomerEntity;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities.StoreEntity;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.repositories.AddressRepository;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.repositories.CityRepository;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.repositories.CustomerRepository;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.repositories.StoreRepository;

@Component
public class ApplicationRepository {

	@Autowired CustomerRepository customerRepository;
	@Autowired CityRepository cityRepository;
	@Autowired StoreRepository storeRepository;
	@Autowired AddressRepository addressRepository;
	
	public CustomerDto findOneCustomerDtoWithMapping(String firstname, String lastname) {
		CustomerEntity customer = customerRepository.findOneByFirstnameIgnoreCaseAndNameIgnoreCase(firstname, lastname);
		return createCustomerDto(customer);
	}
	
	public CustomerDto findOneCustomerDtoWithQuery(String firstname, String lastname) {
		return customerRepository.findOneByFirstnameAndNameAsDto(firstname, lastname);
	}
	
	public void createCustomer(CustomerDto dto) {
		if (!validateCreateParameters(dto)) {
			throw new InvalidCreateParametersException();
		}
		if (customerRepository.findOneByFirstnameIgnoreCaseAndNameIgnoreCase(dto.getFirstname(), dto.getName()) != null) {
			throw new DuplicatedCustomerException();
		}
		if (customerRepository.findOneByEmail(dto.getEmail()) != null) {
			throw new DuplicatedEmailException();
		}
		CityEntity city = cityRepository.findOneByNameAndCountryName(dto.getCity(), dto.getCountry());
		if (city == null) {
			throw new CityNotFoundException();
		}
		StoreEntity store = storeRepository.findOneById(dto.getStore());
		if (store == null) {
			throw new StoreNotFoundException();
		}
		AddressEntity address = addressRepository.findOneByValue(dto.getAddress());
		if (address == null) {
			address = new AddressEntity();
			address.setCity(city);
			address.setValue(dto.getAddress());
			addressRepository.save(address);
		}
		CustomerEntity customer = new CustomerEntity();
		customer.setFirstname(dto.getFirstname());
		customer.setName(dto.getName());
		customer.setAddress(address);
		customer.setEmail(dto.getEmail());
		customer.setStore(store);
		customerRepository.save(customer);
	}
	
	private boolean validateCreateParameters(CustomerDto dto) {
		String firstname = dto.getFirstname();
		String name = dto.getName();
		String email = dto.getEmail();
		String address = dto.getAddress();
		String city = dto.getCity();
		String country = dto.getCountry();
		int store = dto.getStore();
		return firstname != null && !firstname.isBlank()
				&& name != null && !name.isBlank()
				&& email != null && !email.isBlank()
				&& address != null && !address.isBlank()
				&& city != null && !city.isBlank()
				&& country != null && !country.isBlank()
				&& store > 0;
	}
	
	public void updateCustomerEmail(String firstname, String lastname, String email) {
		CustomerEntity customer = customerRepository.findOneByFirstnameIgnoreCaseAndNameIgnoreCase(firstname, lastname);
		if (customer == null) {
			throw new CustomerNotFoundException();
		}
		if (customerRepository.findOneByEmail(email) != null) {
			throw new DuplicatedEmailException();
		}
		customer.setEmail(email);
		customerRepository.save(customer);
	}
	
	public void deleteCustomer(String firstname, String lastname) {
		CustomerEntity customer = customerRepository.findOneByFirstnameIgnoreCaseAndNameIgnoreCase(firstname, lastname);
		if (customer == null) {
			throw new CustomerNotFoundException();
		}
		customerRepository.delete(customer);
	}
	
	private CustomerDto createCustomerDto(CustomerEntity entity) {
		return new CustomerDto(
			entity.getFirstname(), 
			entity.getName(), 
			entity.getEmail(), 
			entity.getAddress().getValue(), 
			entity.getAddress().getCity().getName(), 
			entity.getAddress().getCity().getCountry().getName(), 
			entity.getStore().getId());
	}
}
