package com.digital.common.exception.handler;

import com.digital.common.enums.CustomErrorCode;
import com.digital.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Order
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Handling message not readable", ex);
        String message = "Malformed JSON request";
        String description = "JSON request cannot be deserialized";
        return buildResponseEntity(
                new DigitalApiException(
                        HttpStatus.BAD_REQUEST, CustomErrorCode.MALFORMED_JSON, message, description));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Handling method argument not valid", ex);
        DigitalApiException apiError =
                new DigitalApiException(
                        HttpStatus.BAD_REQUEST,
                        CustomErrorCode.VALIDATION_ERROR,
                        "Validation Error",
                        "There are some data elements missing or invalid");
        apiError.setValidationErrors(
                ex.getBindingResult().getAllErrors().stream()
                        .map(e -> new ValidationError(e))
                        .collect(Collectors.toList()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RequestInvalidException.class)
    public ResponseEntity<Object> handleRequestInvalidException(
            RequestInvalidException requestInvalidException) {
        log.error("Handling method argument not valid", requestInvalidException);
        DigitalApiException apiError =
                new DigitalApiException(
                        HttpStatus.BAD_REQUEST,
                        CustomErrorCode.VALIDATION_ERROR,
                        "Validation Error",
                        "There are some data elements missing or invalid");
        apiError.setValidationErrors(
                requestInvalidException.getValidationError().stream()
                        .map(e -> new ValidationError(e))
                        .collect(Collectors.toList()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex) {
        log.error("Handling method argument not valid", ex);
        DigitalApiException apiError =
                new DigitalApiException(
                        HttpStatus.BAD_REQUEST,
                        CustomErrorCode.VALIDATION_ERROR,
                        "Validation Error",
                        "There are some data elements missing or invalid");
        apiError.setValidationErrors(
                ex.getConstraintViolations().stream()
                        .map(
                                e ->
                                        new ValidationError(
                                                new ObjectError(
                                                        e.getPropertyPath()
                                                                .toString()
                                                                .substring(e.getPropertyPath().toString().indexOf(".") + 1),
                                                        e.getMessage())))
                        .collect(Collectors.toList()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        log.error("Handling resource not found exception", ex);

        DigitalApiException apiError =
                new DigitalApiException(
                        HttpStatus.NOT_FOUND,
                        ex.getCustomErrorCode() != null ? ex.getCustomErrorCode() : CustomErrorCode.RESOURCE_NOT_FOUND,
                        "Resource not found",
                        ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleAllExceptions(CustomException ex) {
        log.error("Handling general exception", ex);
        DigitalApiException apiError =
                new DigitalApiException(
                        ex.getHttpStatus(),
                        ex.getErrorCode(),
                        ex.getMessage(), "");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.error("Handling general exception", ex);
        DigitalApiException apiError =
                new DigitalApiException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        CustomErrorCode.INTERNAL_SERVER_ERROR,
                        "Internal Server Error",
                        ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(DigitalApiException apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
}
