package com.exchange.rate.analyser.api.giphy;

import com.exchange.rate.analyser.util.TestValidationHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GiphyApiHelperTest {

    @Mock
    private GiphyApiFeignClient giphyApi;
    @InjectMocks
    private GiphyApiHelper giphyApiHelper;
    private AutoCloseable closeable;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Validate that GiphyApiHelper class has required fields")
    void validateRequiredFieldExistWithCorrectType() {
        String giphyAppId = "giphyAppId";

        assertTrue(TestValidationHelper.verifyFieldExist(giphyApiHelper, giphyAppId));
        assertTrue(TestValidationHelper.verifyFieldType(giphyApiHelper, GiphyApiFeignClient.class));
    }

    @Test
    @DisplayName("GiphyApiHelper call GiphyApi method to get random gif")
    void getApiRandomGifRespondByTag() {
        when(giphyApi.getGiphyRandom(any(), anyString())).thenReturn("Api was called");
        giphyApiHelper.getApiRandomGifRespondByTag("test");

        verify(giphyApi, times(1)).getGiphyRandom(any(), anyString());
    }
}