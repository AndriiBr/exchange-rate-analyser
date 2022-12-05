package com.exchange.rate.analyser.exception;

/**
 * Occurs as a result of unsuccessful JSON to Object mapping
 */
public class JsonObjectMappingException extends RuntimeException {

    public JsonObjectMappingException() {
    }

    public JsonObjectMappingException(String message) {
        super(message);
    }
}
