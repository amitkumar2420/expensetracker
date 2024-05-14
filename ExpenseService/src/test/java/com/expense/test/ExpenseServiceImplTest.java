package com.expense.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.expense.exception.ExpenseAlreadyExists;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.exception.InvalidExpenseException;
import com.expense.model.Expense;
import com.expense.repository.ExpenseRepo;
import com.expense.service.ExpenseServiceImpl;

class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepo expenseRepo;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense());
        when(expenseRepo.findAll()).thenReturn(expenseList);

        List<Expense> result = expenseService.getAllExpenses();
        assertEquals(1, result.size());
    }

    @Test
    void testPostAllExpenses() throws ExpenseAlreadyExists {
        Expense expense = new Expense();
        expense.setExpenseId(1);
        when(expenseRepo.findByExpenseId(1)).thenReturn(null);
        when(expenseRepo.save(expense)).thenReturn(expense);

        Expense result = expenseService.postAllExpenses(expense);
        assertEquals(1, result.getExpenseId());
    }

    @Test
    void testGetExpenseById() throws InvalidExpenseException {
        Expense expense = new Expense();
        expense.setExpenseId(1);
        when(expenseRepo.findByExpenseId(1)).thenReturn(expense);

        Expense result = expenseService.getExpenseById(1);
        assertEquals(1, result.getExpenseId());
    }

    @Test
    void testDeleteExpenseById() {
        // Assuming the delete operation is successful without any exceptions
        expenseService.deleteExpenseById(1);
    }

    @Test
    void testGetExpenseByName() {
        Expense expense = new Expense();
        expense.setExpensename("TestExpense");
        when(expenseRepo.findByExpensename("TestExpense")).thenReturn(expense);

        Expense result = expenseService.getExpenseByName("TestExpense");
        assertEquals("TestExpense", result.getExpensename());
    }

    @Test
    void testDeleteExpenseByName() {
        when(expenseRepo.findByExpensename("TestExpense")).thenReturn(new Expense());

        boolean result = expenseService.deleteExpenseByName("TestExpense");
        assertTrue(result);
    }

    @Test
    void testUpdateExpenseByName() throws InvalidExpenseException {
        Expense expense = new Expense();
        expense.setExpensename("TestExpense");
        when(expenseRepo.findByExpensename("TestExpense")).thenReturn(expense);
        when(expenseRepo.save(expense)).thenReturn(expense);

        Expense result = expenseService.updateExpenseByName("TestExpense", 500);
        assertEquals(500, result.getTotalExpense());
    }

    

    

    @Test
    void testGetByDate() throws ExpenseNotFoundException {
        Date date = new Date();
        Expense expense = new Expense();
        when(expenseRepo.findByDateOfExpense(date)).thenReturn(expense);

        Expense result = expenseService.getByDate(date);
        assertNotNull(result);
    }

    
}
