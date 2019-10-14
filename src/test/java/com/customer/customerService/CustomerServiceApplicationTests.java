package com.customer.customerService;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.address.Address;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerServiceImpl;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Test
	public void postRequestTest() {
		// Given
		Address address = Address.builder().streetName("Abc").state("Haryana").country("France").build();
		Customer customerRequest = Customer.builder().firstName("Sita").lastName("Ji").address(address).build();
		when(customerRepository.save(customerRequest)).thenReturn(customerRequest);

		// When
		Customer custResponse = customerServiceImpl.createCustomer(customerRequest);

		// Then
		Assert.assertEquals(customerRequest.getFirstName(), custResponse.getFirstName());
	}

	@Test
	public void getRequestTest() {
		// Given
		Address address = Address.builder().streetName("Abc").state("Haryana").country("India").build();
		Address address1 = Address.builder().streetName("Abc").state("Haryana").country("France").build();
		Customer c1 = Customer.builder().firstName("Ram").lastName("Ji")
				.joiningDate(new Date(2019-10-10)).expiryDate(new Date(2029-10-10))
				.address(address).build();
		Customer c2 = Customer.builder().firstName("Sita").lastName("Ji").joiningDate(new Date(2019 - 10 - 10))
				.expiryDate(new Date(2029 - 10 - 10)).address(address1).build();
		List<Customer> customer = new ArrayList<>();
		customer.add(c1);
		customer.add(c2);
		when(customerRepository.findAll()).thenReturn(customer);

		// When
		List<CustomerResponse> customerResponse = customerServiceImpl.getAllCustomers();

		// Then
		Assert.assertNotNull(customerResponse);
		Assert.assertEquals(customerResponse.size(), 2);
		Assert.assertEquals(c1.getFirstName(), customerResponse.get(1).getFirstName());
		Assert.assertEquals("Expired", customerResponse.get(0).getStatus());
		
	}


}
