package com.kaishengit.crm.exception;

public class StaffException extends RuntimeException {

    public StaffException(){}

    public StaffException(String message){
        super(message);
    }

    public StaffException(Throwable th){
        super(th);
    }

    public StaffException(Throwable th,String message){
        super(message,th);
    }
}
