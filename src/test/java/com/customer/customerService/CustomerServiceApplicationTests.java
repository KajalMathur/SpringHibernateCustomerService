package com.customer.customerService;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.address.Address;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

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
        Date date = java.util.Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();
        c.setTime(date);
        c.add(Calendar.YEAR, 2);
        Date expiryDate = c.getTime();
        Address address = Address.builder().streetName("Abc1").state("Haryana").country("India").build();
        Address address1 = Address.builder().streetName("Abc2").state("Delhi").country("France").build();
        Customer c1 = Customer.builder().firstName("Ram").lastName("shaerma").joiningDate(date).expiryDate(expiryDate)
                .address(address).build();
        Customer c2 = Customer.builder().firstName("Sita").lastName("Gupta").joiningDate(date).expiryDate(expiryDate)
                .address(address1).build();
        List<Customer> customer = new ArrayList<>();
        customer.add(c1);
        customer.add(c2);
        when(customerRepository.findAll()).thenReturn(customer);

        // When
        List<CustomerResponse> customerResponseList = customerServiceImpl.getAllCustomers();

        // Then
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
