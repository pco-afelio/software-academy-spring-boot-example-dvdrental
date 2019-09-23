package be.afelio.software_academy.spring_boot.example.dvdrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.CustomerDto;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.ResponseDto;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.ResponseDtoStatus;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.UpdateCustomerEmailDto;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.CityNotFoundException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.CustomerNotFoundException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.DuplicatedCustomerException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.DuplicatedEmailException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.InvalidCreateParametersException;
import be.afelio.software_academy.spring_boot.example.dvdrental.api.exceptions.StoreNotFoundException;
import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.ApplicationRepository;

@Controller
@RequestMapping(value="customer")
public class CustomerController {

	@Autowired ApplicationRepository repository;
	
	@GetMapping(value="{firstname}/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<CustomerDto>> findOne(
			@PathVariable("firstname") String firstname, 
			@PathVariable("name") String lastname) {
		System.out.println(String.format("CustomerController.findOne(%s, %s)", firstname, lastname));
		
		ResponseDto<CustomerDto> dto = null;
		try {
			CustomerDto customer = repository.findOneCustomerDtoWithQuery(firstname, lastname);
			if (customer == null) {
				dto = new ResponseDto<CustomerDto>(ResponseDtoStatus.FAILURE, "customer not found");
			} else {
				dto = new ResponseDto<CustomerDto>(ResponseDtoStatus.SUCCESS, "ok");
				dto.setPayload(customer);
			}
		} catch(Exception e) {
			dto = new ResponseDto<CustomerDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>>  createCustomer(@RequestBody CustomerDto customerDto) {
		ResponseDto<Void> dto = null;
		System.out.println("CustomerController.createCustomer() => " + customerDto);
		
		try {
			repository.createCustomer(customerDto);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "customer created");
		} catch(InvalidCreateParametersException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "invalid create parameters");
		} catch(DuplicatedCustomerException e) {		
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated customer");
		} catch(DuplicatedEmailException e) {		
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated email");
		} catch(CityNotFoundException e) {			
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "city not found");
		} catch(StoreNotFoundException e) {			
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "store not found");
		} catch(Exception e) {			
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> updateCustomerEmail(@RequestBody UpdateCustomerEmailDto updateCustomerEmailDto) {
		ResponseDto<Void> dto = null;
		System.out.println("CustomerController.updateCustomerEmail() => " + updateCustomerEmailDto);
		
		try {
			repository.updateCustomerEmail(updateCustomerEmailDto.getFirstname(), 
					updateCustomerEmailDto.getName(), updateCustomerEmailDto.getEmail());
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "email updated");
		} catch(CustomerNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "user not found");
		} catch(DuplicatedEmailException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated email");
		} catch(Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value="{firstname}/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> deleteCustomer(
			@PathVariable("firstname") String firstname, 
			@PathVariable("name") String lastname) {
		System.out.println("CustomerController.deleteCustomer() => " + firstname + ", " + lastname);
		ResponseDto<Void> dto = null;
		
		try {
			repository.deleteCustomer(firstname, lastname);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "customer deleted");
		} catch(CustomerNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "customer not found");
		} catch(Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
}










