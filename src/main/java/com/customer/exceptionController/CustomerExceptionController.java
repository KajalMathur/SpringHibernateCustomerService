package com.customer.exceptionController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.customer.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerExceptionController {

	@ExceptionHandler(value = CustomerNotFoundException.class )
	public ResponseEntity<String> exception(CustomerNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage() + exception.getErrorCode(), HttpStatus.NOT_FOUND);
	}

}
