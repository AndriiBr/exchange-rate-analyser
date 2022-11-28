package com.exchange.rate.analyser.exception;

public class NoSuchCurrencyRateException extends RuntimeException{

    public NoSuchCurrencyRateException() {
    }

    public NoSuchCurrencyRateException(String message) {
        super(message);
    }
}
