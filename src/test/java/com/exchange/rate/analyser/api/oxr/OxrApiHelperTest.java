package com.exchange.rate.analyser.api.oxr;

import com.exchange.rate.analyser.exception.JsonObjectMappingException;
import com.exchange.rate.analyser.exception.NoSuchCurrencyRateException;
import com.exchange.rate.analyser.util.TestJsonReader;
import com.exchange.rate.analyser.util.TestValidationHelper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OxrApiHelperTest {

    private static final LocalDate DATE = LocalDate.now();
    private static final String NOT_A_JSON_FILE = "not a json file";

    private static String jsonFile;

    @Mock
    private OxrApiFeignClient oxrApi;
    @InjectMocks
    private OxrApiHelper oxrApiHelper;
    private AutoCloseable closeable;

    @BeforeAll
    static void initParameters() throws Exception {
        jsonFile = TestJsonReader.readFileAsString("oxr_2022-11-01.json");
    }

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        when(oxrApi.getExchangeRate(anyString(), any())).thenReturn(jsonFile);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Validate that OxrApiHelper class has required fields")
    void validateRequiredFieldExistWithCorrectType() {
        String oxrAppId = "oxrAppId";

        assertTrue(TestValidationHelper.verifyFieldExist(oxrApiHelper, oxrAppId));
        assertTrue(TestValidationHelper.verifyFieldType(oxrApiHelper, OxrApiFeignClient.class));
    }

    @Test
    @DisplayName("extractRateValue calls External API")
    void extractRateValueCallsApi() {
        oxrApiHelper.extractRateValue(DATE, "eur");

        verify(oxrApi, times(1)).getExchangeRate(anyString(), any());
    }

    @Test
    @DisplayName("extractRateValue returns correct value by currency code")
    void extractRateValue() {
        double eurRate = oxrApiHelper.extractRateValue(DATE, "eur");

        assertEquals(1.012429, eurRate);
    }

    @Test
    @DisplayName("Throw exception when extract exchange for non-existing currency code")
    void extractRateValueThrow() {
        assertThrows(NoSuchCurrencyRateException.class,
                () -> oxrApiHelper.extractRateValue(DATE, "abc"));
    }

    @Test
    @DisplayName("Throw exception if input data is not a json file")
    void jsonMappingException() {
        reset(oxrApi);
        when(oxrApi.getExchangeRate(anyString(), any())).thenReturn(NOT_A_JSON_FILE);

        assertThrows(JsonObjectMappingException.class,
                () -> oxrApiHelper.extractRateValue(DATE, "abc"));
    }
}