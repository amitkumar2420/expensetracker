package com.expense.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExpenseExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String>handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String,String>errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
		
	}
	
	@ExceptionHandler(InvalidExpenseException.class)
	public ResponseEntity<String>notFoundException(){
		return new ResponseEntity<String>("Expense Not found ",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ExpenseAlreadyExists.class)
	public ResponseEntity<String>expenseFoundException(){
		return new ResponseEntity<String>("Expense already exists ",HttpStatus.ALREADY_REPORTED);
		
	}
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<String>handleExpenseNotFoundException(){
		return new ResponseEntity<String>("Expense Not Found ",HttpStatus.NOT_FOUND);
		
	}

}
