package com.inow.csp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inow.csp.config.TokenStorage;
import com.inow.csp.output.client.ClientResponseBean;
import com.inow.csp.service.ICustomerRegistrationService;
import com.inow.csp.service.impl.ClientServiceImpl;

@RestController
public class ClientController {

	
	@Autowired
	TokenStorage tokenStorage;
	
	@Autowired
	ClientServiceImpl clientServiceImpl;
	
	@PostMapping("/generateToken")
	public  ResponseEntity<ClientResponseBean> generateToken() throws Exception {
	    // Generate the token
		ClientResponseBean tokenObject =  clientServiceImpl.fetchClientJWTToken();
		String token  = tokenObject.getJWTToken();
	    tokenStorage.storeToken(token);
	    ClientResponseBean retrievedToken = tokenStorage.retrieveToken();
        return new ResponseEntity<>(retrievedToken, HttpStatus.OK);
	}
	

}
