package com.zenfra.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class GlobalExceptionHandler {
 
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ZenfraException.class)
	public ResponseEntity<?> handleException(ZenfraException exception) {
		logger.error("ZenfraException :: ", exception);
		return new ResponseEntity<>(ErrorResponse.builder().errorMessage(exception.getErrorMessage()).build(),
				exception.getStatus());
	}

}
