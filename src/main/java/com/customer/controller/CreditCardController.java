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
        this.creditCardService = creditCardService;
    }

    /* Post request */
    @PostMapping("/creditCard")
    public ResponseEntity<CreditCard> createCreditcard(@RequestBody CreditCard creditCard) {
        if (creditCard != null)
            return ResponseEntity.ok().body(creditCardService.createCreditCard(creditCard));
        else
            throw new BadRequest("Please provide the valid input parameters");
    }

    /* Get request to fetch the credit cards details */
    @GetMapping("/creditCard")
    public ResponseEntity<List<CreditCard>> getCreditcard(@RequestParam int year) {
        if (year > 0)
            return ResponseEntity.ok(creditCardService.getAllCreditCards(year));
        else
            throw new BadRequest("Please provide the valid input parameters");
    }

    /* Delete Record by Id */
    @DeleteMapping("/creditCard/{id}")
    public ResponseEntity<String> deleteCreditcardById(@PathVariable int id) {
        if (id != 0) {
            creditCardService.deleteCreditCardById(id);
            return ResponseEntity.ok("credit card infomation for id = "+id+" has deleted successfully");
        }
        else
            throw new BadRequest("Please Enter the Valid Id");
    }

    /* Delete all records */
    @DeleteMapping("/creditCard")
    public ResponseEntity<String> deleteAllCreditcard() {
        creditCardService.deleteAllCreditCard();
        return ResponseEntity.ok("All credit card infomation has deleted successfully");
    }
}
