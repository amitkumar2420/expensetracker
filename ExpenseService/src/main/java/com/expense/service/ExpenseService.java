package com.expense.service;

import java.util.Date;
import java.util.List;

import com.expense.exception.ExpenseAlreadyExists;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.exception.InvalidExpenseException;
import com.expense.model.Expense;


public interface ExpenseService {

	public List<Expense> getAllExpenses();

	public Expense postAllExpenses(Expense expense) throws ExpenseAlreadyExists;

	public Expense getExpenseById(int expenseId) throws InvalidExpenseException;

	public void deleteExpenseById(int expenseId);
	
	public Expense getExpenseByName(String expensename);

	public boolean deleteExpenseByName(String expensename);

	public Expense updateExpenseByName(String expensename,long totalExpense) throws InvalidExpenseException;

	public List<Expense> getExpenseByAccount(int account_id);

	public Long totalExpenseBalance();


	public Long totalExpenseByAccountId(int account_id);
	
	public Expense getByDate(Date dateOfExpense) throws ExpenseNotFoundException;

	public long getTotalExpenseByAccountId(int account_id);

	

	
}
