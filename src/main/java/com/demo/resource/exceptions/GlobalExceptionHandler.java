package com.demo.resource.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity handleUserNotFoundException
	(UserNotFoundException userNotFoundException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.NOT_FOUND,userNotFoundException.getMessage());
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity handleRoleNotFoundException
	(RoleNotFoundException roleNotFoundException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.NOT_FOUND,roleNotFoundException.getMessage());
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity handleDuplicateRoleException
	(DuplicateRoleException duplicateRoleException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.BAD_REQUEST ,duplicateRoleException.getMessage());
		return new ResponseEntity(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String fieldName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity handleDataIntegrityViolationException
	(DataIntegrityViolationException dataIntegrityViolationException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.CONFLICT ,dataIntegrityViolationException.getRootCause().getMessage());
		return new ResponseEntity(details,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity handleNoResourceFoundException
	(NoResourceFoundException noResourceFoundException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.NOT_FOUND ,noResourceFoundException.getMessage());
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity handleHttpMessageNotReadableException
	(HttpMessageNotReadableException httpMessageNotReadableException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.NOT_FOUND ,httpMessageNotReadableException.getMessage());
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity handleHttpRequestMethodNotSupportedException
	(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.METHOD_NOT_ALLOWED ,httpRequestMethodNotSupportedException.getMessage());
		return new ResponseEntity(details,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity handleBadCredentialsException
	(BadCredentialsException badCredentialsException) {
		ErrorDetails details=new ErrorDetails(LocalDateTime.now(),HttpStatus.NOT_FOUND ,badCredentialsException.getMessage());
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}
}
