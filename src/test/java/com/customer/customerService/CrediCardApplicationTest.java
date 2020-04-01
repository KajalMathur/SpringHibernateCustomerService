package com.customer.customerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.customer.model.CreditCard;
import com.customer.repository.CreditCardRepository;
import com.customer.service.CreditCardServiceImpl;



public class CrediCardApplicationTest {
	
	CreditCard creditCardData, creditCardDataWithNull, creditCardData1, creditCardData2, creditCardData3, creditCardData4;
	
	List<CreditCard> creditCardList;
	
	CreditCardRepository creditCardRepositoryMock;
	
	CreditCardServiceImpl creditCardServiceImpl;
	
	List<CreditCard> creditCardList1;
	
	List<CreditCard> creditCardList2;
	
	List<CreditCard> creditCardEmptyList;
	
	List<CreditCard> creditCardSortedList;
	
	@Before
	public void dataCreation() {
		creditCardData = CreditCard.builder()
				.cardHolderName("DemoTest")
				.cardNumber("2345678123456101")
				.cvv("123")
				.expiryMonth(8)
				.expiryYear(2025)
				.build();
		
		creditCardRepositoryMock = mock(CreditCardRepository.class);
		
		creditCardServiceImpl = new CreditCardServiceImpl(creditCardRepositoryMock);
		
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
		
		creditCardList = new ArrayList<CreditCard>();
		creditCardList.add(creditCardData);
		creditCardList.add(creditCardData1);
		creditCardList.add(creditCardData2);
		creditCardList.add(creditCardData3);
		creditCardList.add(creditCardData4);
		
		creditCardList1 = new ArrayList<CreditCard>();
		creditCardList1.add(creditCardData);
		
		creditCardList2 = new ArrayList<CreditCard>();
		creditCardList2.add(creditCardData);
		creditCardList2.add(creditCardData1);
		creditCardList2.add(creditCardData2);
		creditCardList2.add(creditCardData3);
		creditCardList2.add(creditCardData4);
		
		creditCardEmptyList = new ArrayList<CreditCard>();
		
		creditCardSortedList = new ArrayList<CreditCard>();
		creditCardSortedList.add(creditCardData4);
		creditCardSortedList.add(creditCardData2);
		creditCardSortedList.add(creditCardData1);
		creditCardSortedList.add(creditCardData3);
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
