package com.inow.csp.output.customerRegistration;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inow.csp.output.ResponseBean;

import lombok.Data;

@Data
public class CustomerRegisterErrorResponseBean implements ResponseBean {
	
	@JsonProperty("Error")
    private Error[] error;

    public Error[] getError() {
        return error;
    }

    public void setError(Error[] error) {
        this.error = error;
    }

    public static class Error {
        @JsonProperty("Message")
        private String message;

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("StackTrace")
        private String stackTrace;

        @JsonProperty("Severity")
        private String severity;
    }
    
    public static CustomerRegisterErrorResponseBean fromErrorMessage(String errorMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(errorMessage, CustomerRegisterErrorResponseBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
