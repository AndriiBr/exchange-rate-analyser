package com.exchange.rate.analyser.controller;

import com.exchange.rate.analyser.sevice.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/rate")
    public String analyzeExchangeRate(@RequestParam(name = "currency") String currency) {
        return exchangeService.returnGifRespond(currency);
    }
}
