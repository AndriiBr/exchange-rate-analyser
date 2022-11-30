package com.exchange.rate.analyser.service;

import com.exchange.rate.analyser.exception.InputValidationException;

public class InputValidator {

    private InputValidator() {
    }

    public static void checkInputCurrencyCode(String currencyCode) throws InputValidationException {
        boolean validationResult = currencyCode.matches("^[a-zA-Z]{3}$");

        if (!validationResult) {
            throw new InputValidationException("Wrong currency code input!");
        }
    }
}
