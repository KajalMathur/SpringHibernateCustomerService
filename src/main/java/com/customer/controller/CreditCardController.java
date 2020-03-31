package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.exception.ForbiddenException;
import com.customer.exception.InvalidRequest;
import com.customer.model.CreditCard;
import com.customer.service.CreditCardServiceImpl;

@RestController
@RequestMapping("/api")
public class CreditCardController {
	
	private CreditCardServiceImpl creditCardServiceImpl;
	
	// Constructor autowiring
	@Autowired
	public CreditCardController(CreditCardServiceImpl creditCardServiceImpl) {
		this.creditCardServiceImpl = creditCardServiceImpl;
	}
	
	/* Post request */
	@PostMapping("/creditCard")
	public ResponseEntity<CreditCard> createCreditCardInfo(@RequestBody CreditCard creditCardInfo) throws InvalidRequest {
		if (creditCardInfo != null) 
			return ResponseEntity.ok().body(creditCardServiceImpl.createCreditCardInfo(creditCardInfo));
		else
			throw new ForbiddenException("Invalid Request");
	}
	
	/* Get request to fetch the credit cards details */
	@GetMapping("/creditCard")
	public ResponseEntity<List<CreditCard>> getCreditCardInfo(@RequestParam int year) throws InvalidRequest {
		if(year > 0) 
		return ResponseEntity.ok(creditCardServiceImpl.getAllCreditCardsInfo(year));
		else
			throw new ForbiddenException("Invalid Request");
	}
}
