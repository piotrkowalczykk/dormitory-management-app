package com.piotrkowalczykk.dormitory_management_app.security.exception;

public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException(String message){
        super(message);
    }
}
