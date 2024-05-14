package com.account.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.account.Exception.AccountAlreadyExistsException;
import com.account.Exception.AccountNotFoundException;
import com.account.controller.AccountController;
import com.account.model.Account;
import com.account.model.AuthRequest;
import com.account.model.CustomResponse;
import com.account.service.AccountService;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test for getAllAccounts
    @Test
    void testGetAllAccounts() {
        // Mocking the service call
        List<Account> accountList = new ArrayList<>();
        when(accountService.getAllAccounts()).thenReturn(accountList);

        // Calling the controller method
        List<Account> result = accountController.getAllAccounts();

        // Asserting the result
        assertEquals(accountList, result);
    }

    // Test for addAccount success scenario
    @Test
    void testAddAccount_Success() throws AccountAlreadyExistsException {
        Account account = new Account();
        when(accountService.addAccount(any(Account.class))).thenReturn(account);

        ResponseEntity<String> response = accountController.addAccount(account);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Registration successful\"}", response.getBody());
    }

    // Test for addAccount failure scenario
    @Test
    void testAddAccount_Failure() throws AccountAlreadyExistsException {
        when(accountService.addAccount(any(Account.class))).thenThrow(AccountAlreadyExistsException.class);

        ResponseEntity<String> response = accountController.addAccount(new Account());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"message\": \"Could not create account, sorry!\"}", response.getBody());
    }

    // Test for getAccountById success scenario
    @Test
    void testGetAccountById_Success() throws AccountNotFoundException {
        Account account = new Account();
        when(accountService.getAccountById(anyInt())).thenReturn(account);
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = accountController.getAccountById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Account);
    }

 

    // Test for updateAccount
    @Test
    void testUpdateAccount() throws AccountNotFoundException {
        Account account = new Account();
        when(accountService.updateAccount(anyInt(), any(Account.class))).thenReturn(account);

        ResponseEntity<Account> response = accountController.updateAccount(1, new Account());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test for getAccountWithoutExpenseById success scenario
    @Test
    void testGetAccountWithoutExpenseById_Success() throws AccountNotFoundException {
        Account account = new Account();
        when(accountService.getAccountWithoutExpenseById(anyInt())).thenReturn(account);

        ResponseEntity<?> response = accountController.getAccountWithoutExpenseById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Account);
    }

    // Test for getAccountWithoutExpenseById failure scenario
    @Test
    void testGetAccountWithoutExpenseById_Failure() throws AccountNotFoundException {
        when(accountService.getAccountWithoutExpenseById(anyInt())).thenThrow(AccountNotFoundException.class);

        ResponseEntity<?> response = accountController.getAccountWithoutExpenseById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof CustomResponse);
    }

    // Test for deleteAccount
    @Test
    void testDeleteAccount() throws AccountNotFoundException {
        ResponseEntity<String> response = accountController.deleteAccount(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Account deleted successfully\"}", response.getBody());
    }

    
    
}
