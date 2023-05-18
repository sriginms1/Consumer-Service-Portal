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

@Service("clientServiceImpl")
public class ClientServiceImpl {
	
	private final WebClient webClient;
	private final TokenStorage tokenStorage;
	
	 @Autowired
	 public ClientServiceImpl(WebClient webClient, TokenStorage tokenStorage) {
	        this.webClient = webClient;
	        this.tokenStorage = tokenStorage;
	    	
	 }
	 
	 public ClientResponseBean fetchClientJWTToken() {
		 String url = Constants.SERVER_HOST + Constants.COREAPI + Constants.CLIENTS_URI_PART + Constants.CLIENT_ID+ "/sessions" ;

		 return webClient.post()
		            .uri(url)
		            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		            .accept(MediaType.APPLICATION_JSON)
		            .retrieve()
		            .bodyToMono(ClientResponseBean.class)
		            .block();
		 
    }
	

}
 


 