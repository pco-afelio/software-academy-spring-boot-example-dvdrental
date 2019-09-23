package be.afelio.software_academy.spring_boot.example.dvdrental.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetCustomerByFirstnameAndNameTest {

	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/customer/Jared/Ely", String.class);
		assertEquals(200, response.getStatusCodeValue());
		
		String json = response.getBody();
		
		// mapper.readValue(json, ResponseDto<CustomerDto>.class)
		TypeReference<ResponseDto<CustomerDto>> type = new TypeReference<ResponseDto<CustomerDto>>() {};
		ResponseDto<CustomerDto> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		
		CustomerDto expected = createJaredEly();
		CustomerDto actual = responseDto.getPayload();
		assertEquals(expected, actual);
	}

	CustomerDto createJaredEly() {
		CustomerDto customer = new CustomerDto("Jared", "Ely", "autrechose@qq.com", 
				"1003 Qinhuangdao Street", "Purwakarta", "Indonesia", 1);
		return customer;
	}
}





