package com.inow.csp.input.customerRegistration;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerRegisterStartRequestBean {

	@JsonProperty("policyNumber")
    private String policyNumber;
}
