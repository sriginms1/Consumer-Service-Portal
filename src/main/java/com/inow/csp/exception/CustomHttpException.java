package com.inow.csp.exception;

import org.springframework.http.HttpStatusCode;

public class CustomHttpException extends Throwable {
	private HttpStatusCode status;
    private String errorMessage;

    public CustomHttpException(HttpStatusCode httpStatusCode, String errorMessage) {
    	super(errorMessage);
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
