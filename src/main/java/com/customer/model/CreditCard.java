package com.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Pattern(regexp = "[0-9]{16}",
            message = "Card Number must be of 16 digits numeric number")
    private String cardNumber;

    @NotNull
    @Pattern(regexp = "[0-9]{3}",
            message = "CVV number must be of 3 digits numeric number")
    private String cvv;

    @NotNull
    @Min(value = 1, message = "month must be atleast 1")
    @Max(value = 12, message = "month cannot more than 12 ")
    private int expiryMonth;

    @NotNull
    @Positive(message = "year must be positive")
    @Min(value = 2020, message = "Year must be atleast 2020")
    private int expiryYear;

    @NotEmpty(message = "Please provide a name")
    private String cardHolderName;
}
