package com.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.CreditCard;
import com.customer.repository.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardRepository creditcardRepository;
	
	// Constructor autowiring
	@Autowired
	public CreditCardServiceImpl(CreditCardRepository creditcardRepository) {
		this.creditcardRepository = creditcardRepository;
	}
	
	@Override
	public CreditCard createCreditCardInfo(CreditCard creditCardInfo) {
		// Here we are saving the credit card details in the database
		return creditcardRepository.save(creditCardInfo);
	}

	@Override
	public List<CreditCard> getAllCreditCardsInfo(int year) {
		List <CreditCard> creditCard = (List<CreditCard>) creditcardRepository.findAll()
				.stream()
				.filter(creditcard -> creditcard.getExpiryYear() == year)
				.sorted((p1,p2) -> { 
					if (p2.getExpiryMonth() > p1.getExpiryMonth())
						return 1;
					else if (p2.getExpiryMonth() < p1.getExpiryMonth())
						return -1;
					else 
						return p2.getCvv().compareTo(p1.getCvv());
				})
				.collect(Collectors.toList());
		return creditCard;
	}
}
