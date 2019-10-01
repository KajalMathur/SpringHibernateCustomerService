package com.customer.customerRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.customerModel.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
