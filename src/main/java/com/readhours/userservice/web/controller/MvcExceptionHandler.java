package com.readhours.userservice.web.controller;

import com.readhours.userservice.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static com.readhours.userservice.utils.ExceptionMessages.DUPLICATE_USERNAME_OR_EMAIL;
import static com.readhours.userservice.utils.ExceptionMessages.USER_NOT_FOUND;

@ControllerAdvice
public class MvcExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());
        ex.getConstraintViolations().forEach(error -> errorsList.add(error.toString()));
        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex){
        return new ResponseEntity<>(DUPLICATE_USERNAME_OR_EMAIL, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usernameNotFoundExceptionHandler(UsernameNotFoundException ex){
        return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}
