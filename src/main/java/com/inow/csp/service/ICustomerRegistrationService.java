package com.inow.csp.service;

import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;

import reactor.core.publisher.Mono;

public interface ICustomerRegistrationService {
	 public Mono<CustomerRegisterStartBean> fetchCustomerRegisterStartInfo(String policyNumber) throws Exception; 
}
