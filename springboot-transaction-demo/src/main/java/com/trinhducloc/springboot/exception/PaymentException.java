package com.trinhducloc.springboot.exception;

public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);
    }
}
