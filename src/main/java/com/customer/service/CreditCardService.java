package com.customer.service;

import java.util.List;

import com.customer.model.CreditCard;

public interface CreditCardService {
	
	public CreditCard createCreditCardInfo(CreditCard creditCardInfo);
	
	List<CreditCard> getAllCreditCardsInfo(int year);
}