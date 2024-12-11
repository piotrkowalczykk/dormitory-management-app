package com.piotrkowalczykk.dormitory_management_app.security.controller;

import com.piotrkowalczykk.dormitory_management_app.security.dto.ResponseGlobalException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionController{
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseGlobalException> handleMethodArgumentNotValidException(Exception exception) {
        String errorMessage = "";
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            errorMessage = e.getBindingResult().getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } else if (exception instanceof HttpMessageNotReadableException) {
            errorMessage += "dateOfBirth: invalid date format";
        } else {
            errorMessage = "unexpected error occurred.";
        }
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.BAD_REQUEST.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseGlobalException> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        String errorMessage = exception.getMessage();
        if(errorMessage.contains("duplicate")){
            errorMessage = "email: email address is already in use";
        } else {
            errorMessage = "data integrity violation";
        }
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.BAD_REQUEST.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
