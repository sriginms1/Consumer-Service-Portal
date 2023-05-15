package com.inow.csp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import com.inow.csp.config.Constants;
import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;
import com.inow.csp.service.ICustomerRegistrationService;



@Service("customerRegistrationServiceImpl")
public class CustomerRegistrationServiceImpl implements ICustomerRegistrationService{

	private final WebClient webClient;
	
	 public CustomerRegistrationServiceImpl(WebClient webClient) {
	        this.webClient = webClient;
	    }
	 
	@Override
	public CustomerRegisterStartBean fetchCustomerRegisterStartInfo(String policyNumber) {
		 String url = Constants.SERVER_HOST + Constants.CUSTOMER_REG_START_URI_PART;
		 String jsonBody = "{\"policyNumber\":\"" + policyNumber + "\"}";
		CustomerRegisterStartBean customerRegisterStart = new CustomerRegisterStartBean();
		return webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(jsonBody))
                .retrieve()
                .bodyToMono(CustomerRegisterStartBean.class)
                .block();
    }
		 
	}
	
}
