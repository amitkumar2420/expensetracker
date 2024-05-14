package com.emailSender.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.emailSender.app.model.AssetDetails;
import com.emailSender.app.model.Email;
import com.emailSender.app.service.EmailSenderService;

@CrossOrigin("*")
@RestController
public class EmailSenderController {
	@Autowired
	private final EmailSenderService emailSenderService;
	AssetDetails asd;
	
	
	public EmailSenderController(EmailSenderService emailSenderService,RestTemplate restTemplate) {
		this.emailSenderService=emailSenderService;
		
	}
	
	@PostMapping("/send-email/{account_id}")
	public ResponseEntity<String> sendEmail(@RequestBody Email email,@PathVariable int account_id) {
		
		//String getUserAssetsUrl = assetMicroServiceUrl + 
		//Double userTotalAssets = restTemplate.getForObject(null, null)
		
		String s=this.emailSenderService.sendEmail(account_id, email.getTo(), email.getSubject(), email.getMessage());
		if (s.equals("You are spending more than your budget! Be aware!")) {
            return new ResponseEntity<>("{\"message\": \"Please check you email\"}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"message\": \"Good Job! You are on track\"}", HttpStatus.NOT_FOUND);
        }
		
		
	}
	

}
