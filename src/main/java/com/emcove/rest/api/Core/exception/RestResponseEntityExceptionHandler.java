package com.emcove.rest.api.Core.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException){
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage;
        if(!violations.isEmpty()){
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" ").append(violation.getMessage()));
            errorMessage = builder.toString();
        }else
            errorMessage = "ConstraintViolationException occurred.";
        constraintViolationException.printStackTrace();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        resourceNotFoundException.printStackTrace();
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessError.class)
    public ResponseEntity<Object> illegalAccessErrorHandler(IllegalAccessError accessError){
        accessError.printStackTrace();
        return new ResponseEntity<>(accessError.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> entityExistsExceptionHandler(EntityExistsException existsException){
        existsException.printStackTrace();
        return new ResponseEntity<>(existsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException){
        illegalArgumentException.printStackTrace();
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public  ResponseEntity<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException httpMessageNotReadableException){
        logger.error(httpMessageNotReadableException.getMessage(),httpMessageNotReadableException);
        return new ResponseEntity<>(httpMessageNotReadableException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}