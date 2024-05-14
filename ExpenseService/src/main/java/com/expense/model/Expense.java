package com.expense.model;



import java.time.LocalDate;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expensecol")
public class Expense {

	@Id
	@Positive(message="Id should be a positive number")
	@NotNull(message="Id should not be null")
	private int expenseId;

	@NotBlank(message = "Expense name cannot be blank")
	private String expensename;

	@Min(value = 0, message = "Total expense cannot be negative")
	private long totalExpense;

	@Pattern(regexp = "^(daily|weekly|monthly)$", message = "Invalid expense frequency")
	private String expense_frequency;

	@Positive(message = "Account ID must be a positive integer")
	private int accountId;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dateOfExpense;

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public LocalDate getDateOfExpense() {
		return dateOfExpense;
	}

	public void setDateOfExpense(LocalDate dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}

	public Expense(
			@Positive(message = "Id should be a positive number") @NotNull(message = "Id should not be null") int expenseId,
			@NotBlank(message = "Expense name cannot be blank") String expensename,
			@Min(value = 0, message = "Total expense cannot be negative") long totalExpense,
			@Pattern(regexp = "^(daily|weekly|monthly)$", message = "Invalid expense frequency") String expense_frequency,
			@Positive(message = "Account ID must be a positive integer") int accountId, LocalDate dateOfExpense) {
		super();
		this.expenseId = expenseId;
		this.expensename = expensename;
		this.totalExpense = totalExpense;
		this.expense_frequency = expense_frequency;
		this.accountId = accountId;
		this.dateOfExpense = dateOfExpense;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


}
