package com.exchange.rate.analyser.controller;

import com.exchange.rate.analyser.sevice.ExchangeRateApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExchangeController {

    private final ExchangeRateApi exchangeRateApi;

    @Autowired
    public ExchangeController(ExchangeRateApi exchangeRateApi) {
        this.exchangeRateApi = exchangeRateApi;
    }

    @GetMapping("/currency")
    public String analyzeExchangeRate() {

        return exchangeRateApi.getRate("2022-11-22", "a72e8be8efdc402ba7a6cec75fbe9dc6");
    }
}
