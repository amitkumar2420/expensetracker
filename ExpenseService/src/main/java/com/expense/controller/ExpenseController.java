package com.expense.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.exception.ExpenseAlreadyExists;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.exception.InvalidExpenseException;
import com.expense.model.Expense;
import com.expense.service.ExpenseService;

import jakarta.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("expense")
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@GetMapping("/all")
	public List<Expense> getAllExpenses() {
		return expenseService.getAllExpenses();
	}

	@PostMapping("/postExpense")
	public ResponseEntity<Expense> postExpense(@Valid @RequestBody Expense expense) throws ExpenseAlreadyExists {
		System.out.println(expense);
		return new ResponseEntity<Expense>(expenseService.postAllExpenses(expense),HttpStatus.OK);
	}

	@GetMapping("/getById/{expenseId}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable int expenseId) throws InvalidExpenseException {
		Expense expense =  expenseService.getExpenseById(expenseId);
		return new ResponseEntity<Expense>(expense,HttpStatus.OK);
	}

	@GetMapping("/getByName/{expensename}")
	public Expense getExpenseByName(@PathVariable String expensename) {
		return expenseService.getExpenseByName(expensename);
	}

	@DeleteMapping("/deleteById/{expenseId}")
	public void deleteExpense(@PathVariable int expenseId) {
		expenseService.deleteExpenseById(expenseId);
	}

	 @DeleteMapping("/deleteByName/{expensename}")
	    public ResponseEntity<String> deleteExpenseByName(@PathVariable String expensename) {
	        boolean deleted = expenseService.deleteExpenseByName(expensename);
	        
	        if (deleted) {
	            return new ResponseEntity<>("{\"message\": \"Expense deleted successfully\"}", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("{\"message\": \"Expense not found or could not be deleted\"}", HttpStatus.NOT_FOUND);
	        }
	    }

	@PutMapping("/updateByName/{expensename}/{totalExpense}")
	public Expense updateExpenseByName(@PathVariable String expensename, @PathVariable long totalExpense)
			throws InvalidExpenseException {
		return expenseService.updateExpenseByName(expensename, totalExpense);
	}

	 @GetMapping("/totalExpenseByAccountId/{account_id}")
	    public ResponseEntity<Long> getTotalExpenseByAccountId(@PathVariable int account_id) {
	        long totalExpense = expenseService.getTotalExpenseByAccountId(account_id);
	        return ResponseEntity.ok(totalExpense);
	    }
	
	@GetMapping("/account/{account_id}")
	public List<Expense> getExpenseByAccount(@PathVariable int account_id) {
		return expenseService.getExpenseByAccount(account_id);
	}

	
	
	@GetMapping("/totalExpense/{account_id}")
	public Long totalExpenseByAccountId(@PathVariable int account_id) {
		return expenseService.totalExpenseByAccountId(account_id);
	}
	
	@GetMapping("/getByDate/{dateOfExpense}")
	public Expense getExpenseByDate(@PathVariable Date dateOfExpense) throws ExpenseNotFoundException {
		return expenseService.getByDate(dateOfExpense);
	}

}
