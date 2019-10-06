package com.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.customerModel.Customer;
import com.customer.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	// get the list of all the customers
	@GetMapping("/customers")
	public List<CustomerResponse> getAllNotes() {

		return customerServiceImpl.getAllCustomers();

	}

	/* Post request */
	@PostMapping("/customers")
	public String createCustomer(@Valid @RequestBody Customer customer) {
		System.out.print("Hello++++" + customer.getAddress());
		customerServiceImpl.createCustomer(customer);
		return "Customer is created successfully";

	}

}
