package com.powerplantsystem.exception;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(NoDataFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new ArrayList<>(), new Date(), ex.getMessage(),
				request.getDescription(false));
		log.error("NoData found for the input: {} ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> invalidInputException(InvalidInputException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		log.error("Invalid input error: {} ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
