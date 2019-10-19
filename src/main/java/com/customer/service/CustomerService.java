package com.customer.service;

import java.util.List;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.model.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer cust);

	public List<CustomerResponse> getAllCustomers();

	public CustomerResponse findCustomerById(int id);
}
