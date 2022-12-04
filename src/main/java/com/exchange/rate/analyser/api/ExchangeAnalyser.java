package com.exchange.rate.analyser.api;

import com.exchange.rate.analyser.api.giphy.GiphyApiHelper;
import com.exchange.rate.analyser.api.oxr.OxrApiHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * The ExchangeAnalyser class provides tools for analysing currency rate changes
 */
@Component
public class ExchangeAnalyser {

    private final OxrApiHelper oxrApiHelper;
    private final GiphyApiHelper giphyApiHelper;

    public ExchangeAnalyser(OxrApiHelper oxrApiHelper, GiphyApiHelper giphyApiHelper) {
        this.oxrApiHelper = oxrApiHelper;
        this.giphyApiHelper = giphyApiHelper;
    }

    /**
     * Gets json with gif based on the result of the exchange rate change
     * @param currencyCode target currency code
     * @return json provided by Giphy API
     */
    public String analyseRateChanges(String currencyCode) {
        String tag = analyseCurrencyDifference(currencyCode);

        return giphyApiHelper.getApiRandomGifRespondByTag(tag);
    }

    /**
     * Checks how the exchange rate has changed for the currency provided
     * @param currencyCode currency code
     * @return tag based on the comparison
     */
    private String analyseCurrencyDifference(String currencyCode) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        double todayRateValue = oxrApiHelper.extractRateValue(today, currencyCode);
        double yesterdayRateValue = oxrApiHelper.extractRateValue(yesterday, currencyCode);

        return todayRateValue < yesterdayRateValue ? "rich" : "broke";
    }
}
