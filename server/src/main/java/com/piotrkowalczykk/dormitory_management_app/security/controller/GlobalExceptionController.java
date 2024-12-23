package com.piotrkowalczykk.dormitory_management_app.security.controller;

import com.piotrkowalczykk.dormitory_management_app.security.dto.ResponseGlobalException;
import com.piotrkowalczykk.dormitory_management_app.security.exception.CustomAuthenticationException;
import com.piotrkowalczykk.dormitory_management_app.security.exception.EmailSendingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionController{
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseGlobalException> handleMethodArgumentNotValidException(Exception exception) {
        String errorMessage = "";
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            Map<String, String> errorDetails = new HashMap<>();
            e.getBindingResult().getFieldErrors().forEach(error -> {
                        if (errorDetails.containsKey(error.getField())){
                            String existingMessages = errorDetails.get(error.getField());
                            errorDetails.put(error.getField(), existingMessages + " " + error.getDefaultMessage());
                        } else {
                            errorDetails.put(error.getField(), error.getDefaultMessage());
                        }
                    }
            );
            if(errorDetails.get("password").contains("mandatory") && errorDetails.get("password").contains("characters")){
                errorDetails.put("password", "password is mandatory");
            }
            errorMessage = errorDetails.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).collect(Collectors.joining(", "));
        } else if (exception instanceof HttpMessageNotReadableException) {
            if(exception.getMessage().contains("Gender")){
                errorMessage = "Invalid gender format. Expected MALE or FEMALE";
            } else if (exception.getMessage().contains("LocalDate")){
                errorMessage = "Invalid date format. Expected yyyy-MM-dd";
            }
        } else {
            errorMessage = exception.getMessage();
        }
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.BAD_REQUEST.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseGlobalException> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        String errorMessage = exception.getMessage();
        if(errorMessage.contains("duplicate")){
            errorMessage += "email: email address is already in use";
        } else {
            errorMessage += "data integrity violation";
        }
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.BAD_REQUEST.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<ResponseGlobalException> handleEmailSendingException(EmailSendingException exception){
        String errorMessage = exception.getMessage();
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseGlobalException> handleIllegalArgumentException(IllegalArgumentException exception){
        String errorMessage = exception.getMessage();
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.BAD_REQUEST.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<ResponseGlobalException> handleCustomAuthenticationException(CustomAuthenticationException exception){
        String errorMessage = exception.getMessage();
        ResponseGlobalException response = new ResponseGlobalException(HttpStatus.UNAUTHORIZED.value(), errorMessage, LocalDate.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
