package com.customer.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.sorting.SortingComparator;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepo;

	@Autowired
	CustomerServiceImpl(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Value("${spring.customer.expDate}")
	private int expDate;

	@Override
	public Customer createCustomer(Customer cust) {
		cust.setExpiryDate(expDate);
		return customerRepo.save(cust);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> custList = customerRepo.findAll();
		List<CustomerResponse> custRespList = custList.stream().map(cus -> {
			CustomerResponse customerResponse = CustomerResponse.builder().id(cus.getId()).firstName(cus.getFirstName())
					.lastName(cus.getLastName()).joiningDate(cus.getJoiningDate()).expiryDate(cus.getExpiryDate())
					.address(cus.getAddress())
					.build();
			customerResponse.setStatus(cus.getJoiningDate(), cus.getExpiryDate());
			return customerResponse;
		}).collect(Collectors.toList());
		Collections.sort(custRespList, new SortingComparator());
		return custRespList;
	}

	@Override
	public Object findCustomerById(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> cus = customerRepo.findById(id);
		if (cus.isPresent()) {
			CustomerResponse customerResponse = CustomerResponse.builder().id(cus.get().getId())
					.firstName(cus.get().getFirstName()).lastName(cus.get().getLastName())
					.joiningDate(cus.get().getJoiningDate()).expiryDate(cus.get().getExpiryDate())
					.address(cus.get().getAddress()).build();
			customerResponse.setStatus(cus.get().getJoiningDate(), cus.get().getExpiryDate());
			return customerResponse;
		}

		else
			return "No data found for the requested user";

	}
}

