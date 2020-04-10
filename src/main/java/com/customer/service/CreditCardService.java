package com.customer.service;

import com.customer.model.CreditCard;

import java.util.List;

public interface CreditCardService {

    public CreditCard createCreditCardInfo(CreditCard creditCardInfo);

    List<CreditCard> getAllCreditCardsInfo(int year);

    public void deleteCreditCardInfoById(int id);

    public void deleteAllCreditCardInfo();
}
