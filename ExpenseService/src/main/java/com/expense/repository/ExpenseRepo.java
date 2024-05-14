package com.expense.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.Expense;
@Repository
public interface ExpenseRepo extends MongoRepository<Expense, Integer>{

	List<Expense> findAll();
	Expense findByExpensename(String expensename);
	Expense findByExpenseId(int expenseId);
	String deleteByExpensename(String expensename);
	
	Expense findByDateOfExpense(Date dateOfExpense);
	//Expense updateByExpensename(String expensename);
	
	List<Expense> findByAccountId(int accountId);
	
}
