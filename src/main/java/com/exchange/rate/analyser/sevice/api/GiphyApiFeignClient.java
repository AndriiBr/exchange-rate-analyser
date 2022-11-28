package com.exchange.rate.analyser.sevice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.api.url}")
public interface GiphyApiFeignClient {

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getGiphyRandom(@RequestParam(name = "api_key") String apiKey,
                          @RequestParam(name = "tag") String tag);

}
