package com.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.account.Exception.AccountAlreadyExistsException;
import com.account.Exception.AccountNotFoundException;
import com.account.Util.JwtUtil;
import com.account.model.Account;
import com.account.model.AuthRequest;
import com.account.model.CustomResponse;
import com.account.service.AccountService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired

	AccountService accountService;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
    private JwtUtil jwtUtil;
	@Autowired
    private AuthenticationManager authenticationManager;


	@GetMapping("/all")

	public List<Account> getAllAccounts() {

		return accountService.getAllAccounts();

	}

	@PostMapping("/postAccount")
	public ResponseEntity<String> addAccount(@Valid @RequestBody Account account) {
	    try {
	        Account acc = accountService.addAccount(account);
	        // Account creation successful, return success message
	        String successMessage = "{\"message\": \"Registration successful\"}";
	        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
	    } catch (AccountAlreadyExistsException e) {
	        // Account creation failed, return failure message
	        String errorMessage = "{\"message\": \"Could not create account, sorry!\"}";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	    }
	}

	@GetMapping("/getAccountById/{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable int accountId) {
	    try {
	        Account account = accountService.getAccountById(accountId);
	        List expenses = this.restTemplate.getForObject("http://ExpenseService/expense/account/" + accountId, List.class);
	        account.setExpenses(expenses);
	        return new ResponseEntity<>(account, HttpStatus.OK);
	    } catch (AccountNotFoundException e) {
	        // Account not found, return a custom message response
	        String message = "{\"message\": \"Account does not exist with us!\"}";
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	    }
	}
	
	
	@PutMapping("/updateAccount/{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable int accountId, @Valid @RequestBody Account updatedAccount) throws AccountNotFoundException {
	    Account account = accountService.updateAccount(accountId, updatedAccount);
	    return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@GetMapping("/getAccountWithoutExpenseById/{accountId}")
	public ResponseEntity<?> getAccountWithoutExpenseById(@PathVariable int accountId) {
	    try {
	        Account account = accountService.getAccountWithoutExpenseById(accountId);
	        return new ResponseEntity<>(account, HttpStatus.OK);
	    } catch (AccountNotFoundException e) {
	        // Account not found, return a custom message response
	        CustomResponse response = new CustomResponse("Account does not exist with us!");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}


	
	@DeleteMapping("/deleteAccount/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable int accountId) throws AccountNotFoundException {
	    accountService.deleteAccount(accountId);
	    return ResponseEntity.ok("{\"message\": \"Account deleted successfully\"}");
	}
	
	@PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Authentication successful, generate JWT token
            final String token = jwtUtil.generateToken(authRequest.getUsername());

            // Return the token in a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
    }

}
