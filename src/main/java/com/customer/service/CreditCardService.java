package com.customer.service;

import com.customer.model.CreditCard;

import java.util.List;

public interface CreditCardService {

    public CreditCard createCreditCard(CreditCard creditCard);

    List<CreditCard> getAllCreditCards(int year);

    public void deleteCreditCardById(int id);

    public void deleteAllCreditCard();
}
