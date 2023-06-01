package com.inow.csp.exception;

public class GlobalErrorResponse {
    private String errorMessage;
    private Integer statusCode;

    public GlobalErrorResponse(String errorMessage, Integer statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

}
