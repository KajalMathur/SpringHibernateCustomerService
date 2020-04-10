package com.customer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {

    private int errorCode;

    public CustomerNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
