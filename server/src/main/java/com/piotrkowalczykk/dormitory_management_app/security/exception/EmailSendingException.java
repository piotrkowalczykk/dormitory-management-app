package com.piotrkowalczykk.dormitory_management_app.security.exception;

public class EmailSendingException extends RuntimeException{
    public EmailSendingException(String message){
        super(message);
    }
}
