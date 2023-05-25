package com.inow.csp.output;

import org.springframework.http.HttpStatusCode;

public class ExceptionBean implements ResponseBean {
    private int statusCode;
    private Object errorMessage;
    
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(Object errorMessage) {
		this.errorMessage = errorMessage;
	}

    
}

