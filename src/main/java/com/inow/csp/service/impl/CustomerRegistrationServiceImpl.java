package com.inow.csp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.inow.csp.config.Constants;
import com.inow.csp.config.TokenStorage;
import com.inow.csp.exception.CustomHttpClientException;
import com.inow.csp.exception.CustomHttpServerException;
import com.inow.csp.exception.TokenExpiredException;
import com.inow.csp.output.client.ClientResponseBean;
import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;
import com.inow.csp.service.ICustomerRegistrationService;

import reactor.core.publisher.Mono;


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
	public CustomerRegisterStartBean fetchCustomerRegisterStartInfo(String policyNumber) throws Exception {
		 String url = Constants.SERVER_HOST + Constants.CUSTOMER_REG_START_URI_PART; 
		 String jsonBody = "{\"policyNumber\":\"" + policyNumber + "\"}";
		 
		 ClientResponseBean token = tokenStorage.retrieveToken();
 
		 return webClient.post()
		            .uri(url)
		            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		            .accept(MediaType.APPLICATION_JSON)
		            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getJWTToken())
		            .body(BodyInserters.fromValue(jsonBody))
		            .retrieve()
		            .onStatus(status -> status.is4xxClientError(), response -> handleClientError(response))
		            .onStatus(status -> status.is5xxServerError(), response -> handleServerError(response))
		            .bodyToMono(CustomerRegisterStartBean.class)
		            .block();
		 
    }
	
	private Mono<? extends Throwable> handleClientError(ClientResponse response) {
	    return response.bodyToMono(String.class)
	            .flatMap(errorBody -> {
	            	if (errorBody.contains("TokenExpiredException")) {
	                    // Handle TokenExpiredException error here
	                    String errorMessage = "Token has been expired";
	                    return Mono.error(new TokenExpiredException(errorMessage));
	                } else {
	            	 String errorMessage = "Client error: " + response.statusCode() + " - " + errorBody;
	                 return Mono.error(new CustomHttpClientException(errorMessage));
	                }
	            });
	}
	
	private Mono<? extends Throwable> handleServerError(ClientResponse response) {
	    return response.bodyToMono(String.class)
	            .flatMap(errorBody -> {
	            	if (errorBody.contains("TokenExpiredException")) {
	                    // Handle TokenExpiredException error here
	                    String errorMessage = "Token has been expired";
	                    return Mono.error(new TokenExpiredException(errorMessage));
	                } else {
	                // Handle server errors here
	                String errorMessage = "Server error: " + response.statusCode() + " - " + errorBody;
	                return Mono.error(new CustomHttpServerException(errorMessage));
	                }
	            });
	}
	                
	
}
