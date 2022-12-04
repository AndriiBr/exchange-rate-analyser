package com.exchange.rate.analyser.api.oxr;

import com.exchange.rate.analyser.exception.JsonObjectMappingException;
import com.exchange.rate.analyser.exception.NoSuchCurrencyRateException;
import com.exchange.rate.analyser.model.OxrDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/**
 * Helper class to process with OpenExchangeRates API
 */
@Component
public class OxrApiHelper {

    private final String oxrAppId = System.getProperty("oxr_app_id");
    private final OxrApiFeignClient oxrApi;

    public OxrApiHelper(OxrApiFeignClient oxrApi) {
        this.oxrApi = oxrApi;
    }


    /**
     * Get exchange rate value for provided currency code and date
     * @param targetDate search date
     * @param currencyCode currency code
     * @return single exchange rate for provided currency
     */
    public double extractRateValue(LocalDate targetDate, String currencyCode) {
        OxrDTO oxr = convertJsonToObjectByDate(targetDate);

        return oxr.getRates().entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(currencyCode))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new NoSuchCurrencyRateException(String
                        .format("%s currency is not detected!", currencyCode)));
    }

    /**
     * Get POJO object from extracted Json for provided date
     * @param targetDate search date
     * @return POJO object
     */
    private OxrDTO convertJsonToObjectByDate(LocalDate targetDate) {
        ObjectMapper objectMapper = new JsonMapper();
        String jsonRespond = getExchangeRateByDate(targetDate);

        try {
            return objectMapper.readValue(jsonRespond, OxrDTO.class);
        } catch (JsonProcessingException e) {
            throw new JsonObjectMappingException("Cannot convert JSON to provided Object instance");
        }
    }

    /**
     * Get daily exchange rate using OpenExchangeRates API by provided date
     * @param targetDate search date.
     * @return JSON with exchange rate data
     */
    private String getExchangeRateByDate(LocalDate targetDate) {
        String date = String.valueOf(targetDate);
        return Objects.requireNonNull(oxrApi.getExchangeRate(date, oxrAppId));
    }
}
