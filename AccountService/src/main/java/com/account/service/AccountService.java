package com.account.service;

import java.util.List;

import javax.validation.Valid;

import com.account.Exception.AccountAlreadyExistsException;
import com.account.Exception.AccountNotFoundException;
import com.account.model.Account;

public interface AccountService {

	public List<Account> getAllAccounts();

	public Account addAccount(Account account) throws AccountAlreadyExistsException;

	//public Optional<Account> getAccountWithExpenses(int account_id);

	public Account getAccountById(int accountId) throws AccountNotFoundException;

	public Account getAccountWithoutExpenseById(int accountId) throws AccountNotFoundException;

	public Account updateAccount(int accountId, @Valid Account updatedAccount) throws AccountNotFoundException;

	public void deleteAccount(int accountId) throws AccountNotFoundException;

	

	
}
