package com.emailSender.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.emailSender.app.external.AssetProxy;
import com.emailSender.app.external.ExpenseProxy;
import com.emailSender.app.model.AssetDetails;
import com.emailSender.app.model.Expense;

@Service

public class EmailSenderServiceImpl implements EmailSenderService {
	@Autowired
	AssetProxy assetproxy;
	@Autowired
	ExpenseProxy expenseproxy;
	AssetDetails det=new AssetDetails();
	Expense e=new Expense();

	private final JavaMailSender mailSender;
	
	public EmailSenderServiceImpl(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	public String sendEmail(int account_id,String to, String subject, String message) {
		Long ass=assetproxy.totalAssetsBalanceByAccountId(account_id);
		Long ex=expenseproxy.getTotalExpenseByAccountId(account_id);
		Long budget=ass/3;
		if(ex>budget) {
			SimpleMailMessage smp=new SimpleMailMessage();
			smp.setFrom("incharakashy@gmail.com");
			smp.setTo(to);
			smp.setSubject(subject);
			smp.setText(message);
			this.mailSender.send(smp);	
			return "You are spending more than your budget! Be aware!";
			
		}
		else {
			return "Good Job, you are on track";
		}
		
		
		
		
	}
	


}
