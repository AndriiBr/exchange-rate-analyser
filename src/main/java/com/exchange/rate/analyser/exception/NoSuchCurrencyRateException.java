package com.exchange.rate.analyser.exception;

/**
 * Occurs when provided source does not have a target currency code.
 */
public class NoSuchCurrencyRateException extends RuntimeException{

    public NoSuchCurrencyRateException() {
    }

    public NoSuchCurrencyRateException(String message) {
        super(message);
    }
}
