package com.customer.service;

import java.util.List;
import java.util.Optional;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.customerModel.Customer;

public interface CustomerService {

	public void createCustomer(Customer cust);

	public List<CustomerResponse> getAllCustomers();

	public Optional<Customer> findCustomerById(int id);

	public void deleteCustomerById(int id);

	public void updateCustomer(Customer cust);

	public void deleteAllCustomers();

	Optional<Customer> findProductById(int id);

	void createProduct(Customer cust);

}
