package com.inow.csp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inow.csp.output.customerRegistration.CustomerRegisterStartBean;
import com.inow.csp.service.ICustomerRegistrationService;

@RestController
public class CustomerRegistrationController {

	@Autowired
	ICustomerRegistrationService customerRegistrationServiceImpl;

	private final ObjectMapper objectMapper;
	
	public CustomerRegistrationController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
	
    @PostMapping("/CustomerRegisterStart")
    public ResponseEntity<CustomerRegisterStartBean> handleCustomerRegisterStart(@RequestBody String requestBody) throws Exception {
    	JsonNode jsonNode = objectMapper.readTree(requestBody);
        String policyNumber = jsonNode.get("policyNumber").asText();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CustomerRegisterStartBean responseBody;
        responseBody = customerRegistrationServiceImpl.fetchCustomerRegisterStartInfo(policyNumber);
        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
    }
}

