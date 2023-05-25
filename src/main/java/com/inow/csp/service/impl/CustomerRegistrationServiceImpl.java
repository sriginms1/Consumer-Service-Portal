package com.inow.csp.service.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inow.csp.annotation.AuthTokenRequired;
import com.inow.csp.config.AuthTokenAspects;
import com.inow.csp.config.Constants;
import com.inow.csp.input.customerRegistration.CustomerRegValidationByAnswerRequestBean;
import com.inow.csp.input.customerRegistration.CustomerRegisterStartRequestBean;
import com.inow.csp.output.ExceptionBean;
import com.inow.csp.output.ResponseBean;
import com.inow.csp.output.customerRegistration.CustomerRegValidationByAnswerResponseBean;
import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;
import com.inow.csp.service.ICustomerRegistrationService;

import reactor.core.publisher.Mono;


@Service("customerRegistrationServiceImpl")
public class CustomerRegistrationServiceImpl implements ICustomerRegistrationService{

	private final AuthTokenAspects authTokenAspects;


	 public CustomerRegistrationServiceImpl(AuthTokenAspects authTokenAspects) {
	        this.authTokenAspects = authTokenAspects;	
	 }
	 
	@Override
	@AuthTokenRequired
	public Mono<? extends ResponseBean> fetchCustomerRegisterStartInfo(String policyNumber)  {
		 
		 ObjectMapper objectMapper = new ObjectMapper();
		 String uri =  Constants.CUSTOMER_REG_START_URI_PART; 
		 String jsonBody = "{\"policyNumber\":\"" + policyNumber + "\"}";
		 CustomerRegisterStartRequestBean requestBody = null;
		try {
			requestBody = objectMapper.readValue(jsonBody, CustomerRegisterStartRequestBean.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return authTokenAspects.makePostRequest(uri, requestBody, CustomerRegisterStartBean.class);
		  
    }
	
	@Override
	@AuthTokenRequired
	public Mono<? extends ResponseBean> validateCustomerRegisterByAnswer(CustomerRegValidationByAnswerRequestBean requestBean)  {
		 
		 String uri =  Constants.VALIDATE_CUSTOMER_REG_BY_ANSWER_URI_PART; 
		 return authTokenAspects.makePostRequest(uri, requestBean, CustomerRegValidationByAnswerResponseBean.class);
				 
		
    }                
	
}
