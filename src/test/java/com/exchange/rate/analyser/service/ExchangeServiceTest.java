package com.exchange.rate.analyser.service;

import com.exchange.rate.analyser.api.ExchangeAnalyser;
import com.exchange.rate.analyser.exception.InputValidationException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ExchangeServiceTest {

    @Mock
    private ExchangeAnalyser exchangeAnalyser;
    @InjectMocks
    private ExchangeService exchangeService;
    private AutoCloseable closeable;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Input validation exist: ExchangeService interrupts execution with incorrect currency code")
    void compareExchangeRateThrowException() {
        try {
            exchangeService.compareExchangeRate("wrong code");
        } catch (InputValidationException e) {
            verify(exchangeAnalyser, never()).analyseRateChanges(anyString());
        }
    }

    @Test
    @DisplayName("ExchangeService call ExchangeAnalyser if currency code is correct")
    void compareExchangeRateUseExchangeAnalyser() {
        exchangeService.compareExchangeRate("uah");
        verify(exchangeAnalyser, times(1)).analyseRateChanges(anyString());
    }
}