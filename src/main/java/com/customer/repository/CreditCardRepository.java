package com.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>  {

}
