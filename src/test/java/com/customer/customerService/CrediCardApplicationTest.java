package com.customer.customerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.customer.model.CreditCard;
import com.customer.repository.CreditCardRepository;
import com.customer.service.CreditCardServiceImpl;
import com.google.common.collect.ImmutableList;

public class CrediCardApplicationTest {
	
	CreditCard creditCardData, creditCardDataWithNull, creditCardData1, creditCardData2, creditCardData3, creditCardData4;
	
	CreditCardRepository creditCardRepositoryMock;
	
	CreditCardServiceImpl creditCardServiceImpl;
	
	ImmutableList<CreditCard> creditCardList;
	
	ImmutableList<CreditCard> creditCardList1;
	
	ImmutableList<CreditCard> creditCardList2;
	
	ImmutableList<CreditCard> creditCardEmptyList;
	
	ImmutableList<CreditCard> creditCardSortedList;
	
	@Before
	public void dataCreation() {
		
		creditCardRepositoryMock = mock(CreditCardRepository.class);
		
		creditCardServiceImpl = new CreditCardServiceImpl(creditCardRepositoryMock);
		
		creditCardData = CreditCard.builder()
				.id(10)
				.cardHolderName("DemoTest")
				.cardNumber("2345678123456101")
				.cvv("123")
				.expiryMonth(8)
				.expiryYear(2025)
				.build();
		
		creditCardData1 = CreditCard.builder()
				.id(1)
				.cardHolderName("DemoTest1")
				.cardNumber("2345678123456101")
				.cvv("123")
				.expiryMonth(8)
				.expiryYear(2021)
				.build();
		
		creditCardData2 = CreditCard.builder()
				.id(2)
				.cardHolderName("DemoTest2")
				.cardNumber("2345678123456101")
				.cvv("124")
				.expiryMonth(8)
				.expiryYear(2021)
				.build();
		
		creditCardData3 = CreditCard.builder()
				.id(3)
				.cardHolderName("DemoTest3")
				.cardNumber("2345678123456101")
				.cvv("123")
				.expiryMonth(4)
				.expiryYear(2021)
				.build();
		
		creditCardData4 = CreditCard.builder()
				.id(4)
				.cardHolderName("DemoTest4")
				.cardNumber("2345678123456101")
				.cvv("123")
				.expiryMonth(12)
				.expiryYear(2021)
				.build();
		
		creditCardDataWithNull = CreditCard.builder()
				.id(5)
				.cardHolderName(null)
				.cardNumber("2345678123456101")
				.cvv("123")
				.expiryMonth(8)
				.expiryYear(2021)
				.build();
		
		creditCardList = ImmutableList.of(creditCardData, creditCardData1, creditCardData2, creditCardData3, creditCardData4);
		
		creditCardList1 = ImmutableList.of(creditCardData);
		
		creditCardList2 = ImmutableList.of(creditCardData, creditCardData1, creditCardData2, creditCardData3, creditCardData4);
		
		creditCardEmptyList = ImmutableList.of();
		
		creditCardSortedList = ImmutableList.of(creditCardData4, creditCardData2, creditCardData1, creditCardData3);
	}
	
	/* Credit Card post request testcases */
	
	@Test
	public void verifyCreditCardPostwithSuccess() {
	        when(creditCardRepositoryMock.save(creditCardData)).thenReturn(creditCardData);
	        assertEquals(creditCardServiceImpl.createCreditCardInfo(creditCardData),creditCardData);
	 }
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void verifyCreditCardPostwithNull() {
	        when(creditCardRepositoryMock.save(null)).thenThrow(IllegalArgumentException.class);
	        creditCardServiceImpl.createCreditCardInfo(null);
	 }
	
	 @Test
	 public void verifyCreditCardPostWithoneNullValue() {
		 when(creditCardRepositoryMock.save(creditCardDataWithNull)).thenReturn(creditCardDataWithNull);
	     assertEquals(creditCardServiceImpl.createCreditCardInfo(creditCardDataWithNull),creditCardDataWithNull);
	 }
	 
	 /* Credit Card get request testcases */
	 @Test
	 public void verifyCreditCardGetWithSuccess() {
		 when(creditCardRepositoryMock.findAll()).thenReturn(creditCardList1);
		 assertEquals(creditCardServiceImpl.getAllCreditCardsInfo(2025),creditCardList1);
	 }
	 
	 @Test
	 public void verifyCreditCardwithFutureYear() {
		 when(creditCardRepositoryMock.findAll()).thenReturn(creditCardEmptyList);
		 assertEquals(creditCardServiceImpl.getAllCreditCardsInfo(3000),creditCardEmptyList);
	 }
	 
	 @Test
	 public void verifyCreditCardDataSortedByDate() {
		 when(creditCardRepositoryMock.findAll()).thenReturn(creditCardList2);
		 assertEquals(creditCardServiceImpl.getAllCreditCardsInfo(2021),creditCardSortedList);
	 }
	 
	 @Test
	 public void verifyCreditCardIfYearisZero() {
		 when(creditCardRepositoryMock.findAll()).thenReturn(creditCardList2);
		 assertEquals(creditCardServiceImpl.getAllCreditCardsInfo(0),creditCardEmptyList);
	 }
}
