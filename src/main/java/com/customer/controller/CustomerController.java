package com.customer.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	CustomerController(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}

	// get the list of all the customers
	@GetMapping("/customers")
	public List<CustomerResponse> getAllCustomers() {
		return customerServiceImpl.getAllCustomers();
	}

	/* Post request */
	@PostMapping("/customerRegister")
	public String createCustomer(@Valid @RequestBody Customer customer) {
		try {
			customerServiceImpl.createCustomer(customer);
		} catch (Exception e) {
			System.out.print("Values can't null");
		}
		return "Customer is created successfully";

	}

	/* get the customer by Id */
	@GetMapping("/customers/{id}")
	public @ResponseBody CustomerResponse findCustomerById(@PathVariable int id, Principal principal) {
		return customerServiceImpl.findCustomerById(id, principal);
	}

	/* update the customer details */
	@PutMapping("/customers/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody Customer customer,
			Principal principal) {

		customerServiceImpl.updateCustomerDetails(id, customer, principal);

		return new ResponseEntity<String>("User is updated successfully", HttpStatus.OK);

	}

	/* Delete the customer details */
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> DeleteCustomerDetails(@PathVariable int id, Principal principal) {
		return customerServiceImpl.deleteCustomer(id, principal);
	}

}
