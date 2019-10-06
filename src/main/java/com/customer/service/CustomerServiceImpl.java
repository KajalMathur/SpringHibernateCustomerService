package com.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.address.Address;
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
		System.out.print(cust.getAddress().getState());
		cust.setExpiryDate(expdate);
		customerRepo.save(cust);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> cust = customerRepo.findAll();
		List<CustomerResponse> custResp = cust.stream().map(cus -> {
			CustomerResponse customerResponse = CustomerResponse.builder().id(cus.getId()).firstName(cus.getFirstName())
					.lastName(cus.getLastName()).joiningDate(cus.getJoiningDate()).expiryDate(cus.getExpiryDate())
					.build();
			customerResponse.setStatus(cus.getJoiningDate(), cus.getExpiryDate());
			Address ad = new Address();
			ad.setAddressId(cus.getAddress().getAddressId());
			ad.setState(cus.getAddress().getState());
			ad.setStreetName(cus.getAddress().getStreetName());
			ad.setCountry(cus.getAddress().getCountry());

			customerResponse.setAddress(ad);

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
