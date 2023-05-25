package com.inow.csp.service;

import com.inow.csp.input.customerRegistration.CustomerRegValidationByAnswerRequestBean;
import com.inow.csp.output.ResponseBean;
import com.inow.csp.output.customerRegistration.CustomerRegValidationByAnswerResponseBean;
import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;

import reactor.core.publisher.Mono;

public interface ICustomerRegistrationService {
	 public Mono<? extends ResponseBean> fetchCustomerRegisterStartInfo(String policyNumber) throws Exception;
	 public Mono<? extends ResponseBean> validateCustomerRegisterByAnswer(CustomerRegValidationByAnswerRequestBean requestBean) throws Exception;
	 
}
