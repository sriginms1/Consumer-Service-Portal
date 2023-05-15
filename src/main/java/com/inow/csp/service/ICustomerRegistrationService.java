package com.inow.csp.service;

import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;

public interface ICustomerRegistrationService {
	 public CustomerRegisterStartBean fetchCustomerRegisterStartInfo(String policyNumber); 
}
