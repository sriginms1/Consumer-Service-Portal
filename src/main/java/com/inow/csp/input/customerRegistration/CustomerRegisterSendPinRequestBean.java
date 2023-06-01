package com.inow.csp.input.customerRegistration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerRegisterSendPinRequestBean {
	@JsonProperty("RegisterToken")
    private String RegisterToken;
	
	@JsonProperty("DestinationTypeCd")
    private String DestinationTypeCd;
	
	@JsonProperty("DestinationCd")
    private String DestinationCd;

}
