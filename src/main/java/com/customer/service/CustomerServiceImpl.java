package com.customer.service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.exception.CustomerNotFoundException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.sorting.SortingComparator;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Value("${spring.customer.expDate}")
	private int expiryDate;

	@Override
	public Customer createCustomer(Customer customer) {
		customer.setExpiryDate(expiryDate);
		customer.setPassword(bcryptEncoder.encode(customer.getPassword()));
		return customerRepository.save(customer);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		List<CustomerResponse> customerResponseList = customerList.stream().map(cus -> {
			CustomerResponse customerResponse = CustomerResponse.builder().id(cus.getId()).firstName(cus.getFirstName())
					.lastName(cus.getLastName()).joiningDate(cus.getJoiningDate()).expiryDate(cus.getExpiryDate())
					.address(cus.getAddress()).build();
			customerResponse.setStatus(cus.getJoiningDate(), cus.getExpiryDate());
			return customerResponse;
		}).collect(Collectors.toList());
		Collections.sort(customerResponseList, new SortingComparator());
		return customerResponseList;
	}

	@Override
	public CustomerResponse findCustomerById(int id, Principal principal) {
		String userName = principal.getName(); // get logged in username
		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isPresent()) {
			if (customer.get().getUserName().equals(userName)) {
				CustomerResponse customerResponse = CustomerResponse.builder().id(customer.get().getId())
						.firstName(customer.get().getFirstName()).lastName(customer.get().getLastName())
						.joiningDate(customer.get().getJoiningDate()).expiryDate(customer.get().getExpiryDate())
						.address(customer.get().getAddress()).build();
				customerResponse.setStatus(customer.get().getJoiningDate(), customer.get().getExpiryDate());
				return customerResponse;
			} else
				throw new CustomerNotFoundException("User cann't access the another other details = ", id);
		} else
			throw new CustomerNotFoundException("Customer not found for id = ", id);
	}

	@Override
	public ResponseEntity<String> updateCustomerDetails(int id, Customer customer, Principal principal) {
		String userName = principal.getName(); // get logged in username
		Optional<Customer> customerRes = customerRepository.findById(id);
		if (customerRes.isPresent()) {
			if (customerRes.get().getUserName().equals(userName) && customerRes.get().getId() == id) {
				customer.setId(id);
				customer.setJoiningDate(customerRes.get().getJoiningDate());
				customer.setExpiryDate(expiryDate);
				customer.setPassword(bcryptEncoder.encode(customer.getPassword()));
				customerRepository.save(customer);
				return new ResponseEntity<String>("User is updated successfully", HttpStatus.OK);
			} else
				throw new CustomerNotFoundException("User cann't access the another user details Id = ", id);
		} else
			throw new CustomerNotFoundException("Customer not found with Id = ", id);

	}

	@Override
	public ResponseEntity<String> deleteCustomer(int id, Principal principal) {
		String userName = principal.getName();
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			if (customer.get().getUserName().equals(userName) && customer.get().getId() == id) {
				customerRepository.deleteById(id);
				return new ResponseEntity<String>("User is deleted successfully", HttpStatus.OK);
			} else
				throw new CustomerNotFoundException("User cann't access the another other details = ", id);
		} else
			throw new CustomerNotFoundException("Customer not found for id = ", id);

	}

}
