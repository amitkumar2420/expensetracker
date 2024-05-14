package com.expense.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.expense.controller.ExpenseController;
import com.expense.exception.ExpenseAlreadyExists;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.exception.InvalidExpenseException;
import com.expense.model.Expense;
import com.expense.service.ExpenseService;

class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        when(expenseService.getAllExpenses()).thenReturn(expenseList);

        List<Expense> result = expenseController.getAllExpenses();

        assertEquals(expenseList, result);
    }

    @Test
    void testPostExpense() throws ExpenseAlreadyExists {
        Expense expense = new Expense();
        when(expenseService.postAllExpenses(any(Expense.class))).thenReturn(expense);

        ResponseEntity<Expense> response = expenseController.postExpense(expense);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    void testGetExpenseById() throws InvalidExpenseException {
        Expense expense = new Expense();
        when(expenseService.getExpenseById(1)).thenReturn(expense);

        ResponseEntity<Expense> response = expenseController.getExpenseById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expense, response.getBody());
    }

    @Test
    void testGetExpenseByName() {
        Expense expense = new Expense();
        when(expenseService.getExpenseByName("ExpenseName")).thenReturn(expense);

        Expense result = expenseController.getExpenseByName("ExpenseName");

        assertEquals(expense, result);
    }

    @Test
    void testDeleteExpense() {
        // Assuming the delete operation is successful without any exceptions
        expenseController.deleteExpense(1);

        verify(expenseService, times(1)).deleteExpenseById(1);
    }

    @Test
    void testDeleteExpenseByName() {
        when(expenseService.deleteExpenseByName("ExpenseName")).thenReturn(true);

        ResponseEntity<String> response = expenseController.deleteExpenseByName("ExpenseName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Expense deleted successfully\"}", response.getBody());
    }

    @Test
    void testUpdateExpenseByName() throws InvalidExpenseException {
        Expense expense = new Expense();
        when(expenseService.updateExpenseByName("ExpenseName", 500)).thenReturn(expense);

        Expense result = expenseController.updateExpenseByName("ExpenseName", 500);

        assertEquals(expense, result);
    }

    @Test
    void testGetTotalExpenseByAccountId() {
        when(expenseService.getTotalExpenseByAccountId(1)).thenReturn(500L);

        ResponseEntity<Long> response = expenseController.getTotalExpenseByAccountId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(500L, response.getBody());
    }

    @Test
    void testGetExpenseByAccount() {
        List<Expense> expenseList = new ArrayList<>();
        when(expenseService.getExpenseByAccount(1)).thenReturn(expenseList);

        List<Expense> result = expenseController.getExpenseByAccount(1);

        assertEquals(expenseList, result);
    }

    @Test
    void testTotalExpenseByAccountId() {
        when(expenseService.totalExpenseByAccountId(1)).thenReturn(500L);

        Long result = expenseController.totalExpenseByAccountId(1);

        assertEquals(500L, result);
    }

    @Test
    void testGetExpenseByDate() throws ExpenseNotFoundException {
        Date date = new Date();
        Expense expense = new Expense();
        when(expenseService.getByDate(date)).thenReturn(expense);

        Expense result = expenseController.getExpenseByDate(date);

        assertEquals(expense, result);
    }
}
