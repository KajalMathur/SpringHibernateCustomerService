package com.customer.service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.ForbiddenException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.sorting.SortingComparator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
				throw new ForbiddenException("Invalid Credentials");
		} else
			throw new CustomerNotFoundException("Customer not found for id = ", id);
	}

	@Override
	public Customer updateCustomerDetails(int id, Customer customer, Principal principal) {
		String userName = principal.getName(); // get logged in username
		Optional<Customer> customerRes = customerRepository.findById(id);
		if (customerRes.isPresent()) {
			if (customerRes.get().getUserName().equals(userName) && customerRes.get().getId() == id) {
				Customer customerResp = customer.builder().id(id).expiryDate(customer.setExpiryDate(expiryDate))
						.joiningDate(customerRes.get().getJoiningDate())
						.password(bcryptEncoder.encode(customer.getPassword())).firstName(customer.getFirstName())
						.lastName(customer.getLastName()).address(customer.getAddress())
						.userName(customer.getUserName()).build();
				return customerRepository.save(customerResp);
			} else
				throw new ForbiddenException("Invalid Credentials");
		} else
			throw new CustomerNotFoundException("Customer not found with Id = ", id);
	}

	@Override
	public void deleteCustomer(int id, Principal principal) {
		String userName = principal.getName();
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			if (customer.get().getUserName().equals(userName) && customer.get().getId() == id) {
				customerRepository.deleteById(id);
			} else
				throw new ForbiddenException("Invalid Credentials");
		} else
			throw new CustomerNotFoundException("Customer not found for id = ", id);
	}

	public Optional<Customer> findCustomerById(int id) {
		return customerRepository.findById(id);
	}
}
