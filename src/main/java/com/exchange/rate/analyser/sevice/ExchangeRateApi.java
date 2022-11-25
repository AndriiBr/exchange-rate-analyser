package com.exchange.rate.analyser.sevice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "exchange", url = "https://openexchangerates.org/api/historical")
public interface ExchangeRateApi {

    @GetMapping(value = "/{date}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getRate(@PathVariable("date") String date, @RequestParam(name = "app_id") String appId);

}
