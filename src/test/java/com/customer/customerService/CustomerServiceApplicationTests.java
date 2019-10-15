package com.customer.customerService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {

	@Mock
	CustomerRepository customerRepository;

	@Mock
	CustomerResponse customerResponse;

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
		Date date = java.util.Calendar.getInstance().getTime();
		Calendar c = Calendar.getInstance();
		Date currentDate = c.getTime();
		c.setTime(date);
		c.add(Calendar.YEAR, 2);
		Date experienceDate = c.getTime();

		Address address = Address.builder().streetName("Abc1").state("Haryana").country("India").build();
		Address address1 = Address.builder().streetName("Abc2").state("Delhi").country("France").build();
		Customer c1 = Customer.builder().firstName("Ram").lastName("shaerma").joiningDate(date)
				.expiryDate(experienceDate).address(address).build();
		Customer c2 = Customer.builder().firstName("Sita").lastName("Gupta").joiningDate(date)
				.expiryDate(experienceDate).address(address1).build();
		List<Customer> customer = new ArrayList<>();
		customer.add(c1);
		customer.add(c2);
		when(customerRepository.findAll()).thenReturn(customer);
		when(customerResponse.setStatus(date, experienceDate)).thenReturn(CustomerResponse.Status.NEW);
		when(customerResponse.getStatus()).thenReturn(CustomerResponse.Status.NEW);

		// When
		List<CustomerResponse> customerResponseList = customerServiceImpl.getAllCustomers();

		// Then
		System.out.print("Joining and expiry date=" + customerResponseList.get(1).getJoiningDate() + " "
				+ customerResponseList.get(1).getExpiryDate());
		Assert.assertNotNull(customerResponseList);
		Assert.assertEquals(customerResponseList.size(), 2);
		Assert.assertEquals(c1.getFirstName(), customerResponseList.get(1).getFirstName());
		Assert.assertEquals(CustomerResponse.Status.NEW, customerResponseList.get(1).getStatus());
		Assert.assertEquals(c1.getLastName(), customerResponseList.get(1).getLastName());
		Assert.assertEquals(date, customer.get(0).getJoiningDate());
		Assert.assertNotEquals(c1.getAddress().getCountry(), customerResponseList.get(0).getAddress().getCountry());
		Assert.assertEquals(c1.getAddress().getCountry(), customerResponseList.get(1).getAddress().getCountry());
	}

}
