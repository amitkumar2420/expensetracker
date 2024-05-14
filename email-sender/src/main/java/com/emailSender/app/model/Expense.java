package com.emailSender.app.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="expensecol")
public class Expense {
	
	@Id
	private int expense_id;
	private String expensename;
	private long totalExpense;
	private String expense_frequency;
	private int account_id;
	
	
	
	
	
	public Expense() {
		
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	
	public String getExpensename() {
		return expensename;
	}
	public void setExpensename(String expensename) {
		this.expensename = expensename;
	}
	public long getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(long totalExpense) {
		this.totalExpense = totalExpense;
	}
	public String getExpense_frequency() {
		return expense_frequency;
	}
	public void setExpense_frequency(String expense_frequency) {
		this.expense_frequency = expense_frequency;
	}
	public Expense(int expense_id, String expensename, long totalExpense, String expense_frequency, int account_id) {
		super();
		this.expense_id = expense_id;
		this.expensename = expensename;
		this.totalExpense = totalExpense;
		this.expense_frequency = expense_frequency;
		this.account_id = account_id;
	}
	@Override
	public String toString() {
		return "Expense [expense_id=" + expense_id + ", expensename=" + expensename + ", totalExpense=" + totalExpense
				+ ", expense_frequency=" + expense_frequency + ", account_id=" + account_id + "]";
	}
	
	
	
}
