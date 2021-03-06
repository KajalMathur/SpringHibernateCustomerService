package com.customer.controller;

import com.customer.exception.BadRequest;
import com.customer.model.CreditCard;
import com.customer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreditCardController {

    private CreditCardService creditCardService;

    // Constructor autowiring
    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        // This is a master change
        this.creditCardService = creditCardService;
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
}
