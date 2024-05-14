package com.account.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "accountcol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Positive(message = "Account ID must be a positive integer")
	@Id
//	@UniqueElements(message="Id should be unique")
	@Indexed(unique = true)
	private int accountId;

	@NotBlank(message = "Account name cannot be blank")
	private String account_name;

	@PositiveOrZero(message = "Salary cannot be negative")
	private float salary;

	@Min(value = 18, message = "Age must be at least 18")
	private int age;

	@Valid
	private List<Expense> expenses = new ArrayList<>();
	
	@NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account(@Positive(message = "Account ID must be a positive integer") int accountId,
			@NotBlank(message = "Account name cannot be blank") String account_name,
			@PositiveOrZero(message = "Salary cannot be negative") float salary,
			@Min(value = 18, message = "Age must be at least 18") int age, @Valid List<Expense> expenses,
			@NotBlank(message = "Username cannot be blank") String username,
			@NotBlank(message = "Password cannot be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") String email) {
		super();
		this.accountId = accountId;
		this.account_name = account_name;
		this.salary = salary;
		this.age = age;
		this.expenses = expenses;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
