package com.emailSender.app.service;

import org.springframework.http.ResponseEntity;

public interface EmailSenderService {
	String sendEmail(int account_id,String to,String subject,String message);

}
