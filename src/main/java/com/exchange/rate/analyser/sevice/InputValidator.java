package com.exchange.rate.analyser.sevice;

import com.exchange.rate.analyser.exception.InputValidationException;

public class InputValidator {

    public static void checkInputCurrencyCode(String currencyCode) throws InputValidationException {
        boolean validationResult = currencyCode.matches("^\\w{3}$");

        if (!validationResult) {
            throw new InputValidationException("Wrong currency code input!");
        }
    }
}
