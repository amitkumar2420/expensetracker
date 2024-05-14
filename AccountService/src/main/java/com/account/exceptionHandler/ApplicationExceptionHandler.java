package com.account.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.account.Exception.AccountAlreadyExistsException;
import com.account.Exception.AccountNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String>handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String,String>errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
		
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String>notFoundException(){
		return new ResponseEntity<String>("Account NOt found ",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<String>alreadyFoundException(){
		return new ResponseEntity<String>("Account with same accountId already exists ",HttpStatus.NOT_FOUND);
		
	}
	

}
