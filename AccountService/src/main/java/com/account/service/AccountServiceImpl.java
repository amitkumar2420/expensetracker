package com.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.Exception.AccountAlreadyExistsException;
import com.account.Exception.AccountNotFoundException;
import com.account.model.Account;
import com.account.repo.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepo accountRepo;

	@Override
	public List<Account> getAllAccounts() {

		return accountRepo.findAll();
	}

	@Override
	public Account addAccount(Account account) throws AccountAlreadyExistsException {

		Account acc = accountRepo.findByAccountId(account.getAccountId());
		if (acc != null) {
			throw new AccountAlreadyExistsException();
		}
		return accountRepo.save(account);
	}

	@Override
	public Account getAccountById(int accountId) throws AccountNotFoundException {

		Account a = accountRepo.findByAccountId(accountId);
		if (a == null) {
			throw new AccountNotFoundException();
		}
		return a;

	}

	@Override
	public Account getAccountWithoutExpenseById(int accountId) throws AccountNotFoundException {
		Account a = accountRepo.findByAccountId(accountId);
		if (a == null) {
			throw new AccountNotFoundException();
		}
		return a;
	}

	  @Override
	    public Account updateAccount(int accountId, Account updatedAccount) throws AccountNotFoundException {
	        // Check if the account with the given accountId exists
	        Account existingAccount = accountRepo.findByAccountId(accountId);
	        if(existingAccount==null) {
	        	throw new AccountNotFoundException();
	        }

	        // Update the account fields
	        existingAccount.setAccount_name(updatedAccount.getAccount_name());
	        existingAccount.setSalary(updatedAccount.getSalary());
	        existingAccount.setAge(updatedAccount.getAge());
	        existingAccount.setEmail(updatedAccount.getEmail());
	        existingAccount.setUsername(updatedAccount.getUsername());
	        existingAccount.setPassword(updatedAccount.getPassword());

	        // Save the updated account
	        return accountRepo.save(existingAccount);
	    }
	  
	  
	  @Override
	    public void deleteAccount(int accountId) throws AccountNotFoundException {
	        // Check if the account exists
	        Account existingAccount = accountRepo.findByAccountId(accountId);
	        if(existingAccount==null) {
	        	throw new AccountNotFoundException();
	        }

	        // Delete the account
	        accountRepo.delete(existingAccount);
	    }

}
