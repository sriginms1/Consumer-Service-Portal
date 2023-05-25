package com.inow.csp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.inow.csp.output.ExceptionBean;

public class ExceptionUtils {
	public static ExceptionBean createExceptionBean(int statusCode, String errorMessage) {
        ExceptionBean exceptionBean = new ExceptionBean();
        exceptionBean.setStatusCode(statusCode);
        exceptionBean.setErrorMessage(errorMessage);
        return exceptionBean;
	}
}
