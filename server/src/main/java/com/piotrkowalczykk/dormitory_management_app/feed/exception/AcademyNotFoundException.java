package com.piotrkowalczykk.dormitory_management_app.feed.exception;

public class AcademyNotFoundException extends RuntimeException{
    public AcademyNotFoundException(String message){
        super(message);
    }
}
