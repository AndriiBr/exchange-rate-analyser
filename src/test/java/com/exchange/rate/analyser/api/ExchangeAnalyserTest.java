package com.exchange.rate.analyser.api;

import com.exchange.rate.analyser.api.giphy.GiphyApiHelper;
import com.exchange.rate.analyser.api.oxr.OxrApiHelper;
import com.exchange.rate.analyser.util.TestValidationHelper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExchangeAnalyserTest {

    private static final String UAH = "uah";
    private static final String EUR = "eur";
    private static final String FAKE_RICH_GIF_RESPOND = "fake RICH gif respond";
    private static final String FAKE_BROKE_GIF_RESPOND = "fake BROKE gif respond";
    private static final double FAKE_UAH_TODAY_RATE = 1.33333;
    private static final double FAKE_UAH_YESTERDAY_RATE = 2.4444;
    private static final double FAKE_EUR_TODAY_RATE = 5.33333;
    private static final double FAKE_EUR_YESTERDAY_RATE = 2.4444;
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate YESTERDAY = TODAY.minusDays(1);

    @Mock
    private OxrApiHelper oxrApiHelper;
    @Mock
    private GiphyApiHelper giphyApiHelper;
    @InjectMocks
    private ExchangeAnalyser exchangeAnalyser;
    private AutoCloseable closeable;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        when(giphyApiHelper.getApiRandomGifRespondByTag("rich")).thenReturn(FAKE_RICH_GIF_RESPOND);
        when(giphyApiHelper.getApiRandomGifRespondByTag("broke")).thenReturn(FAKE_BROKE_GIF_RESPOND);

        when(oxrApiHelper.extractRateValue(TODAY, UAH)).thenReturn(FAKE_UAH_TODAY_RATE);
        when(oxrApiHelper.extractRateValue(YESTERDAY, UAH)).thenReturn(FAKE_UAH_YESTERDAY_RATE);

        when(oxrApiHelper.extractRateValue(TODAY, EUR)).thenReturn(FAKE_EUR_TODAY_RATE);
        when(oxrApiHelper.extractRateValue(YESTERDAY, EUR)).thenReturn(FAKE_EUR_YESTERDAY_RATE);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Validate that ExchangeAnalyser class has required fields")
    void validateRequiredFieldExistWithCorrectType() {
        assertTrue(TestValidationHelper.verifyFieldType(exchangeAnalyser, OxrApiHelper.class));
        assertTrue(TestValidationHelper.verifyFieldType(exchangeAnalyser, GiphyApiHelper.class));
    }

    @Test
    @DisplayName("Returns 'rich' respond if today`s rate is higher")
    void analyseRateChangesRich() {
        assertEquals(FAKE_RICH_GIF_RESPOND, exchangeAnalyser.analyseRateChanges(UAH));
    }

    @Test
    @DisplayName("Returns 'broke' respond if today`s rate is lower")
    void analyseRateChangesBroke() {
        assertEquals(FAKE_BROKE_GIF_RESPOND, exchangeAnalyser.analyseRateChanges(EUR));
    }
}