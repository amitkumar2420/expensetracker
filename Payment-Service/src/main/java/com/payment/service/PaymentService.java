package com.payment.service;

import com.payment.models.TransactionDetails;

public interface PaymentService {
	
	TransactionDetails createTransaction(Double amount);

}
