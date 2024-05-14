package com.email.test;

import com.emailSender.app.model.AssetDetails;
import com.emailSender.app.controller.EmailSenderController;
import com.emailSender.app.model.Email;
import com.emailSender.app.service.EmailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EmailSenderControllerTest {

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private EmailSenderController emailSenderController;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendEmail() {
        Email email = new Email();
        email.setTo("test@example.com");
        email.setSubject("Test Subject");
        email.setMessage("Test Message");

        when(emailSenderService.sendEmail(anyInt(), anyString(), anyString(), anyString())).thenReturn("You are spending more than your budget! Be aware!");

        ResponseEntity<String> response = emailSenderController.sendEmail(email, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Please check you email\"}", response.getBody());
    }

    @Test
    void testSendEmailNotOnTrack() {
        Email email = new Email();
        email.setTo("test@example.com");
        email.setSubject("Test Subject");
        email.setMessage("Test Message");

        when(emailSenderService.sendEmail(anyInt(), anyString(), anyString(), anyString())).thenReturn("Good Job! You are on track");

        ResponseEntity<String> response = emailSenderController.sendEmail(email, 1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("{\"message\": \"Good Job! You are on track\"}", response.getBody());
    }
}
