package com.customer.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.customer.CustomerResponse.CustomerResponse;
import com.customer.model.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer cust);

	public List<CustomerResponse> getAllCustomers();

	public void deleteCustomer(int id, Principal principal);

	Customer updateCustomerDetails(int id, Customer customer, Principal principal);

	CustomerResponse findCustomerById(int id, Principal pricipal);

	public CustomerResponse findCustomerById(int id);
}
