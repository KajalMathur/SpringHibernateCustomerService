package com.customer.exception;

public class InvalidRequest extends RuntimeException {

    String message;

    public InvalidRequest(String message) {
        super(message);
    }
}
