package com.demo.resource.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDetails {

	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	
	public ErrorDetails(LocalDateTime timestamp, HttpStatus status, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}
	
	
}
