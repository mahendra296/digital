package com.digital.common.exception;

import com.digital.common.enums.CustomErrorCode;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
    private final CustomErrorCode customErrorCode;

    public CustomException(String message, CustomErrorCode customErrorCode, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.customErrorCode = customErrorCode;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public CustomErrorCode getErrorCode() {
        return customErrorCode;
    }
}
