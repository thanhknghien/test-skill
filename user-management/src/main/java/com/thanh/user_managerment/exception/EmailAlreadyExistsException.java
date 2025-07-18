package com.thanh.user_managerment.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String ex)
    {
        super(ex);
    }
}