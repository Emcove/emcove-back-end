package com.emcove.rest.api.Core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

//@ControllerAdvice
public class RestResponseEntityExceptionHandler  /*extends ResponseEntityExceptionHandler */{

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException){
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if(!violations.isEmpty()){
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" " + violation.getMessage() ));
            errorMessage = builder.toString();
        }else
            errorMessage = "ConstraintViolationException ocurred.";
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
        httpMessageNotReadableException.printStackTrace();
        return new ResponseEntity<>(httpMessageNotReadableException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String errorMessage = "";
        if(!fieldErrors.isEmpty()){
            StringBuilder builder = new StringBuilder();
            fieldErrors.forEach(fieldError -> builder.append(fieldError.getDefaultMessage()));
            errorMessage = builder.toString();
        }else
            errorMessage = "MethodArgumentNotValidException ocurred.";

        ex.printStackTrace();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    */
}
