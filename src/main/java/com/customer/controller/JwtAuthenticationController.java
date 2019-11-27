package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.exception.ForbiddenException;
import com.customer.jwtconfig.JwtTokenUtil;
import com.customer.model.Customer;
import com.customer.model.JwtResponse;
import com.customer.service.JwtUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
/*
 * For supporting CORS @CrossOrigin is supported by SpringBoot and by
 * default @CrossOrigin allows all origin and HTTP methods specified
 */
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Customer authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String userName, String password) throws Exception, ForbiddenException {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
	}
}