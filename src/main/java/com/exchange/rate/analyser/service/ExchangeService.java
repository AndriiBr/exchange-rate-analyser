package com.exchange.rate.analyser.service;

import com.exchange.rate.analyser.api.ExchangeAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    private final ExchangeAnalyser exchangeAnalyser;

    @Autowired
    public ExchangeService(ExchangeAnalyser exchangeAnalyser) {
        this.exchangeAnalyser = exchangeAnalyser;
    }

    public String compareExchangeRate(String currency) {
        InputValidator.validateInputCurrencyCode(currency);

        return exchangeAnalyser.analyseRateChanges(currency);
    }
}
