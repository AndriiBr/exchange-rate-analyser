package com.exchange.rate.analyser.sevice;

import com.exchange.rate.analyser.exception.NoSuchCurrencyRateException;
import com.exchange.rate.analyser.model.OxrPOJO;
import com.exchange.rate.analyser.sevice.api.GiphyApiFeignClient;
import com.exchange.rate.analyser.sevice.api.OxrApiFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ExchangeService {

    private final String oxrAppId = System.getProperty("oxr_app_id");
    private final String giphyAppId = System.getProperty("giphy_app_id");

    private final OxrApiFeignClient oxrApi;
    private final GiphyApiFeignClient giphyApi;

    @Autowired
    public ExchangeService(OxrApiFeignClient oxrApi, GiphyApiFeignClient giphyApi) {
        this.oxrApi = oxrApi;
        this.giphyApi = giphyApi;
    }

    public String returnGifRespond(String currency) {
        InputValidator.checkInputCurrencyCode(currency);

        return analyzeExchangeRateDifference(currency);
    }

    private String analyzeExchangeRateDifference(String currency) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        double todayRateValue = extractRateValue(today, currency);
        double yesterdayRateValue = extractRateValue(yesterday, currency);

        String tag = defineRequestTag(todayRateValue, yesterdayRateValue);

        return giphyApi.getGiphyRandom(giphyAppId, tag);
    }

    private OxrPOJO convertRespondObject(LocalDate targetDate) {
        ObjectMapper objectMapper = new JsonMapper();
        String jsonRespond = oxrApi.getExchangeRate(String.valueOf(targetDate), oxrAppId);

        try {
            return objectMapper.readValue(jsonRespond, OxrPOJO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String defineRequestTag(double todayRateValue, double yesterdayRateValue) {
        return todayRateValue > yesterdayRateValue ? "rich" : "broke";
    }

    private double extractRateValue(LocalDate targetDate, String currency) {
        OxrPOJO oxr = convertRespondObject(targetDate);

        return oxr.getRates().entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(currency))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new NoSuchCurrencyRateException(String.format("%s currency is not detected!", currency)));
    }
}
