package com.account.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.account.Exception.AccountAlreadyExistsException;
import com.account.Exception.AccountNotFoundException;
import com.account.model.Account;
import com.account.repo.AccountRepo;
import com.account.service.AccountServiceImpl;

public class AccountServiceImplTest {

    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Validator validator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        // Create a validator factory
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(1, "John", 50000, 30, new ArrayList<>(), "john", "password", "john@example.com"));
        accountList.add(new Account(2, "Alice", 60000, 28, new ArrayList<>(), "alice", "password", "alice@example.com"));

        when(accountRepo.findAll()).thenReturn(accountList);

        List<Account> result = accountService.getAllAccounts();

        assertEquals(accountList, result);
    }

    @Test
    public void testAddAccount() throws AccountAlreadyExistsException {
        Account newAccount = new Account(3, "Bob", 55000, 32, new ArrayList<>(), "bob", "password", "bob@example.com");

        when(accountRepo.findByAccountId(newAccount.getAccountId())).thenReturn(null);
        when(accountRepo.save(newAccount)).thenReturn(newAccount);

        var violations = validator.validate(newAccount);
        assertTrue(violations.isEmpty(), "Account is not valid as per validation annotations.");

        Account result = accountService.addAccount(newAccount);

        assertEquals(newAccount, result);
    }

    @Test
    public void testAddAccountAlreadyExists() {
        Account existingAccount = new Account(4, "Charlie", 60000, 35, new ArrayList<>(), "charlie", "password", "charlie@example.com");

        when(accountRepo.findByAccountId(existingAccount.getAccountId())).thenReturn(existingAccount);

        assertThrows(AccountAlreadyExistsException.class, () -> accountService.addAccount(existingAccount));
    }

    @Test
    public void testGetAccountById() throws AccountNotFoundException {
        int accountId = 1;
        Account account = new Account(accountId, "John", 50000, 30, new ArrayList<>(), "john", "password", "john@example.com");

        when(accountRepo.findByAccountId(accountId)).thenReturn(account);

        Account result = accountService.getAccountById(accountId);

        assertEquals(account, result);
    }

    @Test
    public void testGetAccountByIdNotFound() {
        int accountId = 5;

        when(accountRepo.findByAccountId(accountId)).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(accountId));
    }

    @Test
    public void testGetAccountWithoutExpenseById() throws AccountNotFoundException {
        int accountId = 2;
        Account account = new Account(accountId, "Alice", 60000, 28, new ArrayList<>(), "alice", "password", "alice@example.com");

        when(accountRepo.findByAccountId(accountId)).thenReturn(account);

        Account result = accountService.getAccountWithoutExpenseById(accountId);

        assertEquals(account, result);
    }

    @Test
    public void testUpdateAccount() throws AccountNotFoundException {
        int accountId = 3;
        Account existingAccount = new Account(accountId, "Bob", 55000, 32, new ArrayList<>(), "bob", "password", "bob@example.com");
        Account updatedAccount = new Account(accountId, "Updated Bob", 60000, 33, new ArrayList<>(), "updated_bob", "new_password", "updated_bob@example.com");

        when(accountRepo.findByAccountId(accountId)).thenReturn(existingAccount);
        when(accountRepo.save(existingAccount)).thenReturn(updatedAccount);

        var violations = validator.validate(updatedAccount);
        assertTrue(violations.isEmpty(), "Updated account is not valid as per validation annotations.");

        Account result = accountService.updateAccount(accountId, updatedAccount);

        assertEquals(updatedAccount, result);
    }

    @Test
    public void testUpdateAccountNotFound() {
        int accountId = 4;
        Account updatedAccount = new Account(accountId, "Updated Charlie", 65000, 36, new ArrayList<>(), "updated_charlie", "new_password", "updated_charlie@example.com");

        when(accountRepo.findByAccountId(accountId)).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> accountService.updateAccount(accountId, updatedAccount));
    }

    @Test
    public void testDeleteAccount() throws AccountNotFoundException {
        int accountId = 2;
        Account account = new Account(accountId, "Alice", 60000, 28, new ArrayList<>(), "alice", "password", "alice@example.com");

        when(accountRepo.findByAccountId(accountId)).thenReturn(account);

        accountService.deleteAccount(accountId);

        verify(accountRepo, times(1)).delete(account);
    }

    @Test
    public void testDeleteAccountNotFound() {
        int accountId = 5;

        when(accountRepo.findByAccountId(accountId)).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccount(accountId));
    }
}
