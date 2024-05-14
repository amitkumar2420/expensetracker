package com.emailSender.app.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emailSender.app.model.Expense;



@FeignClient(name = "ExpenseService", url = "http://localhost:2222/expense")

@Service
public interface ExpenseProxy {
	@GetMapping("/totalExpenseByAccountId/{account_id}")
	public Long getTotalExpenseByAccountId(@PathVariable int account_id);

}
