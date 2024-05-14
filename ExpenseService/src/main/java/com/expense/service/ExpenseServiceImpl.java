package com.expense.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.exception.ExpenseAlreadyExists;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.exception.InvalidExpenseException;
import com.expense.model.Expense;
import com.expense.repository.ExpenseRepo;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseRepo expenseRepo;

	private Expense expense;

	@Override
	public List<Expense> getAllExpenses() {
		// TODO Auto-generated method stub
		return expenseRepo.findAll();
	}

	@Override
	public Expense postAllExpenses(Expense expense) throws ExpenseAlreadyExists {
		// TODO Auto-generated method stub
		Expense ex=expenseRepo.findByExpenseId(expense.getExpenseId());
		if(ex!=null) {
			throw new ExpenseAlreadyExists();
		}
		System.out.println(expense);
		return expenseRepo.save(expense);
	}

	@Override
	public Expense getExpenseById(int expenseId) throws InvalidExpenseException {
		// TODO Auto-generated method stub
		Expense expense = expenseRepo.findByExpenseId(expenseId);
		if(expense==null) {
			throw new InvalidExpenseException();
		}
		return expense;
	}

	@Override
	public void deleteExpenseById(int expenseId) {
		// TODO Auto-generated method stub
		expenseRepo.deleteById(expenseId);
		// return "The Expense with id: "+expenseId+" deleted";
	}

	@Override
	public Expense getExpenseByName(String expensename) {
		// TODO Auto-generated method stub
		return expenseRepo.findByExpensename(expensename);
	}

	@Override
	public boolean deleteExpenseByName(String expensename) {
	    Expense expense = expenseRepo.findByExpensename(expensename);
	    
	    if (expense != null) {
	        expenseRepo.delete(expense);
	        return true; // Expense was found and deleted
	    } else {
	        return false; // Expense was not found
	    }
	}

	@Override
	public Expense updateExpenseByName(String expensename, long totalExpense) throws InvalidExpenseException {
		// TODO Auto-generated method stub
		Expense e = expenseRepo.findByExpensename(expensename);
		if (e == null) {
			throw new InvalidExpenseException();
		}
		e.setTotalExpense(totalExpense);
		return expenseRepo.save(e);

	}

	@Override
	public List<Expense> getExpenseByAccount(int account_id) {
		// TODO Auto-generated method stub
		List<Expense> list = expenseRepo.findAll();
		List<Expense> list2 = new ArrayList<>();
		for (Expense e : list) {
			if (e.getAccountId() == account_id) {
				list2.add(e);
			}
		}

		return list2;
	}

	@Override
	public Long totalExpenseBalance() {

		List<Expense> list = expenseRepo.findAll();

		Long sum = 0L;

		for (Expense ad : list) {

			sum += ad.getTotalExpense();

		}

		return sum;

	}

	@Override
	public Long totalExpenseByAccountId(int account_id) {
		List<Expense> list = expenseRepo.findAll();
		List<Expense> list1 = new ArrayList<>();

		Long sum = 0L;
		for (Expense ex : list) {
			if (ex.getAccountId() == account_id) {
				list1.add(ex);
			}

		}
		for (Expense ex : list1) {
			sum = sum + ex.getTotalExpense();
		}
		return sum;
	}

	@Override
	public Expense getByDate(Date dateOfExpense) throws ExpenseNotFoundException {
		Expense ex = expenseRepo.findByDateOfExpense(dateOfExpense);
		
		if(ex==null)
		{
			throw new ExpenseNotFoundException();
		}
		return ex;
	}

	@Override
    public long getTotalExpenseByAccountId(int accountId) {
        List<Expense> expenses = expenseRepo.findByAccountId(accountId);
        long totalExpense = 0L;

        for (Expense expense : expenses) {
            totalExpense += expense.getTotalExpense();
        }

        return totalExpense;
    }
}
