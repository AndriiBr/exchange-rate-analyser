package com.exchange.rate.analyser.api.giphy;

import org.springframework.stereotype.Component;

/**
 * Helper class to process with Giphy API
 */
@Component
public class GiphyApiHelper {

    private final String giphyAppId = System.getProperty("giphy_app_id");
    private final GiphyApiFeignClient giphyApi;

    public GiphyApiHelper(GiphyApiFeignClient giphyApi) {
        this.giphyApi = giphyApi;
    }

    /**
     * Get random gif using Giphy API by provided keyword
     * @param tag search keyword.
     * @return JSON with gif
     */
    public String getApiRandomGifRespondByTag(String tag) {
        return giphyApi.getGiphyRandom(giphyAppId, tag);
    }
}
