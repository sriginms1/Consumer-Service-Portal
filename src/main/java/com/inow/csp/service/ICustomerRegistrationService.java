package com.inow.csp.service;

import com.inow.csp.input.customerRegistration.CustomerRegValidationByAnswerRequestBean;
import com.inow.csp.output.customerRegistration.CustomerRegValidationByAnswerResponseBean;
import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;

import reactor.core.publisher.Mono;

public interface ICustomerRegistrationService {
	 public Mono<CustomerRegisterStartBean> fetchCustomerRegisterStartInfo(String policyNumber) throws Exception;
	 public Mono<CustomerRegValidationByAnswerResponseBean> validateCustomerRegisterByAnswer(CustomerRegValidationByAnswerRequestBean requestBean) throws Exception;
	 
}
