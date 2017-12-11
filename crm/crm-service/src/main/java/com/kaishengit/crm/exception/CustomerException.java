package com.kaishengit.crm.exception;

public class CustomerException extends RuntimeException {

    public CustomerException(){}

    public CustomerException(String message){
        super(message);
    }

    public CustomerException(Throwable th){
        super(th);
    }

    public CustomerException(Throwable th,String message){
        super(message,th);
    }
}
