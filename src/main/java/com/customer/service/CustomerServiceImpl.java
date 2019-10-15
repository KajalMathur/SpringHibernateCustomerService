package com.customer.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Value("${spring.customer.expDate}")
	private int expiryDate;

	@Override
	public Customer createCustomer(Customer customer) {
		customer.setExpiryDate(expiryDate);
		return customerRepository.save(customer);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		List<CustomerResponse> customerResponseList = customerList.stream().map(cus -> {
			CustomerResponse customerResponse = CustomerResponse.builder().id(cus.getId()).firstName(cus.getFirstName())
					.lastName(cus.getLastName()).joiningDate(cus.getJoiningDate()).expiryDate(cus.getExpiryDate())
					.address(cus.getAddress())
					.build();
			customerResponse.setStatus(cus.getJoiningDate(), cus.getExpiryDate());
			return customerResponse;
		}).collect(Collectors.toList());
		Collections.sort(customerResponseList, new SortingComparator());
		return customerResponseList;
	}

	@Override
	public CustomerResponse findCustomerById(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			CustomerResponse customerResponse = CustomerResponse.builder().id(customer.get().getId())
					.firstName(customer.get().getFirstName()).lastName(customer.get().getLastName())
					.joiningDate(customer.get().getJoiningDate()).expiryDate(customer.get().getExpiryDate())
					.address(customer.get().getAddress()).build();
			customerResponse.setStatus(customer.get().getJoiningDate(), customer.get().getExpiryDate());
			return customerResponse;
		}
		else
			throw new CustomerNotFoundException("");
	}
}

