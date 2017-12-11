package com.kaishengit.crm.exception;

public class DepartmentException extends RuntimeException{
    public DepartmentException(){}

    public DepartmentException(String message){
        super(message);
    }

    public DepartmentException(Throwable th){
        super(th);
    }

    public DepartmentException(Throwable th,String message){
        super(message,th);
    }
}
