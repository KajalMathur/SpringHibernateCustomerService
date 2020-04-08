package com.customer.service;

import com.customer.model.CreditCard;
import com.customer.repository.CreditCardRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
@ConfigurationProperties(prefix = "spring.datasource")
public class CreditCardServiceImpl implements CreditCardService {

    private CreditCardRepository creditcardRepository;

    private String username;

    // Constructor autowiring
    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditcardRepository) {
        this.creditcardRepository = creditcardRepository;
    }

    @Override
    public CreditCard createCreditCardInfo(CreditCard creditCardInfo) {
        // Here we are saving the credit card details in the database
        log.info(username);
        return creditcardRepository.save(creditCardInfo);
    }

    @Override
    public List<CreditCard> getAllCreditCardsInfo(int year) {
        List<CreditCard> creditCard = (List<CreditCard>) creditcardRepository.findAll()
                .stream()
                .filter(creditcard -> creditcard.getExpiryYear() == year)
                .sorted((p1, p2) -> {
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

    @Override
    public void deleteCreditCardInfoById(int id) {
        // TODO Auto-generated method stub
        creditcardRepository.deleteById(id);
    }

    @Override
    public void deleteAllCreditCardInfo() {
        // TODO Auto-generated method stub
        creditcardRepository.deleteAll();
    }
}
