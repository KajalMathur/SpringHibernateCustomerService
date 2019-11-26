package com.customer.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Customer user = customerRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		return new User(user.getUserName(), user.getPassword(),new ArrayList<>());
	}
}