package be.afelio.software_academy.spring_boot.example.dvdrental.controller;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PostCustomer {

	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		try {
			CustomerDto customerDto = createBettyBoop();
			RequestEntity<CustomerDto> requestEntity 
				= new RequestEntity<CustomerDto>(customerDto, HttpMethod.POST, URI.create("/customer"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			
			String json = response.getBody();
			
			// mapper.readValue(json, ResponseDto<CustomerDto>.class)
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkBettyBoopCreated());
		} finally {
			jdbcTemplate.update("delete from customer where first_name = 'Betty' and last_name = 'Boop'");
			jdbcTemplate.update("delete from address where address = 'Hollywood bld, 100'");
		}
	}

	CustomerDto createBettyBoop() {
		CustomerDto customer = new CustomerDto("Betty", "Boop", "betty.boop@qq.com", 
				"Hollywood bld, 100", "Purwakarta", "Indonesia", 1);
		return customer;
	}
	
	boolean checkBettyBoopCreated() {
		boolean created = false;
		Integer id = jdbcTemplate.queryForObject("select customer_id from customer "
				+ "where first_name = 'Betty' and last_name = 'Boop'", Integer.class);
		created = id != null;
		return created;
	}
}





