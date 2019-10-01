package com.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.customerModel.Customer;
import com.customer.customerRepo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	@Value("${spring.customer.expDate}")
	private int expdate;

	@Override
	public void createCustomer(Customer cust) {
		cust.setExpiryDate(expdate);
		customerRepo.save(cust);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> cust = customerRepo.findAll();
		List<CustomerResponse> custResp = cust.stream().map(cus -> {
			CustomerResponse customerResponse = CustomerResponse.builder().id(cus.getId()).firstName(cus.getAddress())
					.lastName(cus.getLastName()).address(cus.getAddress()).joiningDate(cus.getJoiningDate())
					.expiryDate(cus.getExpiryDate()).build();
			customerResponse.setStatus(cus.getJoiningDate(), cus.getExpiryDate());
			return customerResponse;
		}).collect(Collectors.toList());
		return custResp;
	}

	@Override
	public Optional<Customer> findCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomerById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(Customer cust) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllCustomers() {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Customer> findProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProduct(Customer cust) {
		customerRepo.save(cust);
	}

}
