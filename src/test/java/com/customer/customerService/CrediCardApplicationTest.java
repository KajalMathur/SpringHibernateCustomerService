package com.customer.customerService;

import com.customer.model.CreditCard;
import com.customer.repository.CreditCardRepository;
import com.customer.service.CreditCardServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;



public class CrediCardApplicationTest {
	
	CreditCard creditCardData;
	
	@Before
	public void dataCreation() {
		creditCardData = CreditCard.builder()
								.cardHolderName("DemoTest")
								.cardNumber("2345678123456101")
								.cvv("123")
								.expiryMonth(8)
								.expiryYear(2025)
								.build();
		}
	

	 @Test
	 public void verifyCreditCardPostwithSuccess() {
	        CreditCardRepository creditCardRepositoryMock = mock(CreditCardRepository .class);
	        when(creditCardRepositoryMock.save(creditCardData)).thenReturn(creditCardData);
	        CreditCardServiceImpl creditCardServiceImpl = new CreditCardServiceImpl(creditCardRepositoryMock);
	        creditCardServiceImpl.createCreditCardInfo(creditCardData);
	        assertEquals(creditCardServiceImpl.createCreditCardInfo(creditCardData),creditCardData);
	    }
}
