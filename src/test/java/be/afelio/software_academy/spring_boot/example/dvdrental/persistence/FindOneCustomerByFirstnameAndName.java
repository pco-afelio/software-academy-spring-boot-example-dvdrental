package be.afelio.software_academy.spring_boot.example.dvdrental.persistence;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.software_academy.spring_boot.example.dvdrental.api.dto.CustomerDto;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class FindOneCustomerByFirstnameAndName {

	@Autowired ApplicationRepository repository;
	
	@Test
	public void test() {
		CustomerDto expected = createJaredEly();
		CustomerDto actual = repository.findOneCustomerDtoWithQuery("Jared", "Ely");
		assertEquals(expected, actual);
		
	}

	CustomerDto createJaredEly() {
		CustomerDto customer = new CustomerDto("Jared", "Ely", "autrechose@qq.com", 
				"1003 Qinhuangdao Street", "Purwakarta", "Indonesia", 1);
		return customer;
	}
}





