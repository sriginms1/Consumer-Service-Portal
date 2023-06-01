package com.inow.csp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		String errorMessage = ex.getMessage();
		Integer errorCode = HttpStatus.NOT_FOUND.value();
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomNullPointerException.class)
    public ResponseEntity<GlobalErrorResponse> handleCustomNullPointerException(CustomNullPointerException ex) {
    	String errorMessage = ex.getMessage();
    	Integer errorCode = HttpStatus.BAD_REQUEST.value();
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleException(Exception ex) {
    	String errorMessage = ex.getMessage();
    	Integer errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
