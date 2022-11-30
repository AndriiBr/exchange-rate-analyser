package com.exchange.rate.analyser.service;

import com.exchange.rate.analyser.exception.InputValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputValidatorTest {

    @ParameterizedTest
    @DisplayName("throw exception for wrong currency code")
    @MethodSource("provideWrongCurrencyCodes")
    void checkInputCurrencyCodeThrowsExceptionForWrongCode(String currencyCode) {
        assertThrows(InputValidationException.class,
                () -> InputValidator.checkInputCurrencyCode(currencyCode));
    }

    @ParameterizedTest
    @DisplayName("do not throw exception for correct currency code")
    @MethodSource("provideCorrectCurrencyCodes")
    void heckInputCurrencyCodeThrowsExceptionForCorrectCode(String currencyCode) {
        assertDoesNotThrow(() -> InputValidator.checkInputCurrencyCode(currencyCode));
    }

    static Stream<Arguments> provideWrongCurrencyCodes() {
        return Stream.of(
                Arguments.arguments("usdt"),
                Arguments.arguments("us"),
                Arguments.arguments("123"),
                Arguments.arguments("G4e"),
                Arguments.arguments("U")
        );
    }

    static Stream<Arguments> provideCorrectCurrencyCodes() {
        return Stream.of(
                Arguments.arguments("usd"),
                Arguments.arguments("USD"),
                Arguments.arguments("UsD"),
                Arguments.arguments("usD"),
                Arguments.arguments("uSd")
        );
    }
}