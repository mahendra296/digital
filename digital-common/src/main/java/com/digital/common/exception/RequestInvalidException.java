package com.digital.common.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class RequestInvalidException extends Exception {

    public List<ObjectError> validationErrors;

    public String errorMsg;

    public RequestInvalidException(List<ObjectError> validationErrors) {
        super("Validation errors : " + validationErrors);
        this.validationErrors = validationErrors;
    }

    public RequestInvalidException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public List<ObjectError> getValidationError() {
        return validationErrors;
    }
}
