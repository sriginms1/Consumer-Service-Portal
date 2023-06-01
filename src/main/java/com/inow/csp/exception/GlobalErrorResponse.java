package com.inow.csp.exception;

public class GlobalErrorResponse {
    private String errorMessage;
    private String errorCode;

    public GlobalErrorResponse(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
