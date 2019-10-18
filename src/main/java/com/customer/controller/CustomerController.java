package com.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.model.Customer;
import com.customer.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	CustomerController(CustomerServiceImpl customerServiceImpl)
	{
		this.customerServiceImpl = customerServiceImpl;
	}

	// get the list of all the customers
	@GetMapping("/customers")
	public List<CustomerResponse> getAllCustomers() {

		return customerServiceImpl.getAllCustomers();

	}

	/* Post request */
	@PostMapping("/customers")
	public String createCustomer(@Valid @RequestBody Customer customer) {
		customerServiceImpl.createCustomer(customer);
		return "Customer is created successfully";

	}

	/* get the customer by Id */
	@GetMapping("/customers/{id}")
	public @ResponseBody CustomerResponse findCustomerById(@PathVariable int id) {
		return customerServiceImpl.findCustomerById(id);
	}

}
