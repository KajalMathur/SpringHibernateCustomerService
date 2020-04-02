package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.exception.BadRequest;
import com.customer.model.CreditCard;
import com.customer.service.CreditCardService;

@RestController
@RequestMapping("/api")
public class CreditCardController {
	
	private CreditCardService creditCardService;
	
	// Constructor autowiring
	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		// This is a master change
		this.creditCardService = creditCardService;
		// This is a feature branch
	}
	
	/* Post request */
	@PostMapping("/creditCard")
	public ResponseEntity<CreditCard> createCreditCardInfo(@RequestBody CreditCard creditCardInfo) {
		if (creditCardInfo != null) 
			return ResponseEntity.ok().body(creditCardService.createCreditCardInfo(creditCardInfo));
		else
			throw new BadRequest("Please provide the valid input parameters");
	}
	
	/* Get request to fetch the credit cards details */
	@GetMapping("/creditCard")
	public ResponseEntity<List<CreditCard>> getCreditCardInfo(@RequestParam int year) {
		if (year > 0) 
			return ResponseEntity.ok(creditCardService.getAllCreditCardsInfo(year));
		else
			throw new BadRequest("Please provide the valid input parameters");
		}
	
	/* Delete Record by Id */
	@DeleteMapping("/creditCard/{id}")
	public ResponseEntity<String> deleteCreditCardById(@PathVariable int id) {
		if (id != 0) {
			creditCardService.deleteCreditCardInfoById(id);
			return ResponseEntity.ok("credit card infomation for id = "+id+" has deleted successfully");
		}
		else
			throw new BadRequest("Please Enter the Valid Id");
	}
	
	/* Delete all records */
	@DeleteMapping("/creditCard")
	public ResponseEntity<String> deleteAllCreditCard() {
			creditCardService.deleteAllCreditCardInfo();
			return ResponseEntity.ok("All credit card infomation has deleted successfully");
	}
}
