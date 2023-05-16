package com.inow.csp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inow.csp.config.TokenStorage;
import com.inow.csp.output.client.ClientResponseBean;

@RestController
public class ClientController {

	@Autowired
	TokenStorage tokenStorage;
	
	@PostMapping("/generateToken")
	public  ResponseEntity<Object> generateToken() {
	    // Generate the token
	    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Ikd1aWRld2lyZSBTb2Z0d2FyZSBJbmMuIiwiZXhwIjoxNjg0MjQ3MTEzfQ.St2HTkl7KOYWOvjauA9a9wXMb2CzuA7XsgM5zLUK2ChOGTPAW3SsSJYwcono-aaEv7ChV2-DAMK01XdDg0dn8MH8maZ2rVf2h-IzgPt5niMW8l-F-vOtkZmgqt9J3jpUXX0XXC7FGNDLgl14vjR0Z9HGERfKtkEQOvOomBot9iGfXuEqmUkRvbtMdHmaDk4uiztgHXijGMfA1R7PyRFqmY-d3T9T1T74e5x4yO1ROiXO2xD0jOJmi5zBvVPcMow414cr9DqzosAzrEOuvjLvV2UcnBA_hmp_2BcEcBA5bn2SYecEaK-vEJc4SOAzZ32KNE61P_mOGfr5EYESVoH7kw";	    
	    // Store the token
	    tokenStorage.storeToken(token);
        String retrievedToken = tokenStorage.retrieveToken();

        // Create a JSON response with the retrieved token
        ClientResponseBean response = new ClientResponseBean(retrievedToken);

        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
