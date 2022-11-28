package com.exchange.rate.analyser.sevice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "exchange", url = "${oxr.api.url}")
public interface OxrApiFeignClient {

    @GetMapping(value = "/{date}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getExchangeRate(@PathVariable(name = "date") String date,
                           @RequestParam(name = "app_id") String appId);

}
