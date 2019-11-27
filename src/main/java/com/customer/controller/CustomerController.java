package com.customer.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.customer.exception.ForbiddenException;
import com.customer.exception.InvalidRequest;
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

	/* get the list of all the customers */
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
		return ResponseEntity.ok().body(customerServiceImpl.getAllCustomers());
	}

	/* Post request */
	@PostMapping("/customerRegister")
	public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer) throws InvalidRequest {
		if (customer != null) {
			customerServiceImpl.createCustomer(customer);
			return ResponseEntity.ok().build();
		} else
			throw new ForbiddenException("Please provide valid credentials");
	}

	/* get the customer by Id */
	@GetMapping("/customers/{id}")
	public @ResponseBody ResponseEntity<CustomerResponse> findCustomerById(@PathVariable int id, Principal principal) {
		return ResponseEntity.ok().body(customerServiceImpl.findCustomerById(id, principal));
	}

	/* update the customer details */
	@PutMapping("/customers/{id}")
	public ResponseEntity<Void> updateCustomer(@PathVariable int id, @RequestBody Customer customer,
			Principal principal) {
		customerServiceImpl.updateCustomerDetails(id, customer, principal);
		return ResponseEntity.ok().build();
	}

	/* Delete the customer details */
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> DeleteCustomerDetails(@PathVariable int id, Principal principal) {
		 customerServiceImpl.deleteCustomer(id, principal);
		 return ResponseEntity.ok().body("Customer has deleted successfully");
	}

	/* get the customer by Id */
	@GetMapping("/customer/{id}")
	public @ResponseBody ResponseEntity<CustomerResponse> findCustomerById(@PathVariable int id) {
		return ResponseEntity.ok().body(customerServiceImpl.findCustomerById(id));
	}
}
