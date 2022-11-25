package com.exchange.rate.analyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeRateAnalyserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateAnalyserApplication.class, args);
    }

}
