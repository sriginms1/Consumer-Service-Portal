package com.inow.csp.exception;

import org.springframework.http.HttpStatusCode;

public class CustomHttpClientException extends CustomHttpException {
	private HttpStatusCode status;
    private String errorMessage;

    public CustomHttpClientException(HttpStatusCode httpStatusCode, String errorMessage) {
    	super(httpStatusCode, errorMessage);
        this.status = httpStatusCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
