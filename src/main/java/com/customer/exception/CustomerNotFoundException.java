package com.customer.exception;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException {

	private int errorCode;

	public CustomerNotFoundException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
