package com.customer.exceptionController;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.customer.exception.BadRequest;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.ForbiddenException;
import com.customer.exception.InvalidRequest;

@ControllerAdvice
public class CustomerExceptionController {

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<String> exception(CustomerNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()+exception.getErrorCode());
	}

	@ExceptionHandler(value = InvalidRequest.class)
	public ResponseEntity<String> exception(InvalidRequest exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity<String> exception(ForbiddenException exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
	}
	
	@ExceptionHandler(value = BadRequest.class)
	public ResponseEntity<String> exception(BadRequest exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<String> exception(ConstraintViolationException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		return ResponseEntity.status(500).body(exception.getMessage());
	}
}
