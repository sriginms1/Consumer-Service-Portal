package com.inow.csp.input.customerRegistration;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerRegValidationByAnswerRequestBean {

	@JsonProperty("RegisterToken")
    private String RegisterToken;
	
	@JsonProperty("Zip")
    private String Zip;
	
	@JsonProperty("PremiumAmount")
    private Double PremiumAmount;
	
	@JsonProperty("CustomerNumber")
    private Integer CustomerNumber;
	
	@JsonProperty("PhoneNumber")
    private String PhoneNumber;
	
	@JsonProperty("LastBilledAmount")
    private Double LastBilledAmount;
}

