package com.zenfra.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ZenfraException extends Exception { 

	private HttpStatus status;

	private String errorMessage;

	public ZenfraException(HttpStatus status, String errorMessage) {
		super(errorMessage);
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public ZenfraException(HttpStatus status, String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		this.status = status;
		this.errorMessage = errorMessage;
	}
}
