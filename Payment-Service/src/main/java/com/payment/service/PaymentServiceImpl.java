package com.payment.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.payment.models.TransactionDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final String KEY = "rzp_test_CHYMw5ZQAytc3F";
	private static final String KEY_SECRET = "eiEemjgNhUcGXXKpsN2Qk2Is";
	private static final String CURRENCY = "INR";
	private static final int Payment_capture=1;

	/**
	 * Method to create transaction
	 */
	public TransactionDetails createTransaction(Double amount) {

		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount * 100));
			jsonObject.put("currency", CURRENCY);
			jsonObject.put("payment_capture", Payment_capture);

			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

			Order order = razorpayClient.orders.create(jsonObject);
			TransactionDetails transactionDetails = prepareTrasactionDetails(order);
			return transactionDetails;
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TransactionDetails prepareTrasactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		int amount = order.get("amount");
		
		System.out.println(order);
		TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount,KEY);
		return transactionDetails;
	}

}