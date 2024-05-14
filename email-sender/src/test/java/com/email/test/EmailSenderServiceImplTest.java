package com.email.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.emailSender.app.external.AssetProxy;
import com.emailSender.app.external.ExpenseProxy;
import com.emailSender.app.service.EmailSenderServiceImpl;

class EmailSenderServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private AssetProxy assetProxy;

    @Mock
    private ExpenseProxy expenseProxy;

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendEmailWhenExpenseExceedsBudget() {
        // Mock data and behavior
        int accountId = 123;
        String to = "test@example.com";
        String subject = "Subject";
        String message = "Message";

        when(assetProxy.totalAssetsBalanceByAccountId(accountId)).thenReturn(300L);
        when(expenseProxy.getTotalExpenseByAccountId(accountId)).thenReturn(200L);

        String result = emailSenderService.sendEmail(accountId, to, subject, message);

        assertEquals("You are spending more than your budget! Be aware!", result);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendEmailWhenExpenseIsWithinBudget() {
        // Mock data and behavior
        int accountId = 123;
        String to = "test@example.com";
        String subject = "Subject";
        String message = "Message";

        when(assetProxy.totalAssetsBalanceByAccountId(accountId)).thenReturn(300L);
        when(expenseProxy.getTotalExpenseByAccountId(accountId)).thenReturn(80L);

        String result = emailSenderService.sendEmail(accountId, to, subject, message);

        assertEquals("Good Job, you are on track", result);
        verify(mailSender, never()).send(any(SimpleMailMessage.class));
    }
}
