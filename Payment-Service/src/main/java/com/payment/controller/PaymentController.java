 package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.models.TransactionDetails;
import com.payment.service.PaymentServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")

public class PaymentController {

    @Autowired
    private PaymentServiceImpl service;

    @GetMapping("/createTransaction/{membershipType}")
    public TransactionDetails createTransaction(@PathVariable("membershipType") String membershipType) {
        Double amount = calculateAmountByMembershipType(membershipType);
        if (amount == null) {
            // Handle invalid membershipType
            return null;
        }
        return service.createTransaction(amount);
    }

    private Double calculateAmountByMembershipType(String membershipType) {
        switch (membershipType) {
            case "silver":
                return 50.0;
            case "gold":
                return 100.0;
            case "platinum":
                return 200.0;
            default:
                // Handle invalid membership types
                return null;
        }
    }
}

	
	 
	
	
	

