package com.exchange.rate.analyser.exception;

public class InputValidationException extends RuntimeException{

    public InputValidationException(){
        super();
    }

    public InputValidationException(String message) {
        super(message);
    }
}
