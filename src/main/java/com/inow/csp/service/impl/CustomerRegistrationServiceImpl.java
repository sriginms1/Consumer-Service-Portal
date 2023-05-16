package com.inow.csp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import com.inow.csp.config.Constants;
import com.inow.csp.config.TokenStorage;
import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;
import com.inow.csp.service.ICustomerRegistrationService;


@Service("customerRegistrationServiceImpl")
public class CustomerRegistrationServiceImpl implements ICustomerRegistrationService{

	private final WebClient webClient;
	private final TokenStorage tokenStorage;
	
	 @Autowired
	 public CustomerRegistrationServiceImpl(WebClient webClient, TokenStorage tokenStorage) {
	        this.webClient = webClient;
	        this.tokenStorage = tokenStorage;
	    	
	 }
	 
	@Override
	public CustomerRegisterStartBean fetchCustomerRegisterStartInfo(String policyNumber) {
		 String url = Constants.SERVER_HOST + Constants.CUSTOMER_REG_START_URI_PART; 
		 String jsonBody = "{\"policyNumber\":\"" + policyNumber + "\"}";
		 
		 String token = tokenStorage.retrieveToken();
		 return webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(BodyInserters.fromValue(jsonBody))
                .retrieve()
                .bodyToMono(CustomerRegisterStartBean.class)
                .block();
    }
	
}
