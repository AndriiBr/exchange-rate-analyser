package com.exchange.rate.analyser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OxrDTO {

    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}