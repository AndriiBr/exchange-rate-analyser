package com.exchange.rate.analyser.exception;

/**
 * Occurs as a result of unsuccessful validation
 */
public class InputValidationException extends RuntimeException{

    public InputValidationException(){
        super();
    }

    public InputValidationException(String message) {
        super(message);
    }
}
