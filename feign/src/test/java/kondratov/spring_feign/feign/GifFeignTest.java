package kondratov.spring_feign.feign;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.LinkedHashMap;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

import static kondratov.spring_feign.feign.PropertyService.properties;

class GifFeignTest {
    private String app_id = properties().getProperty("id_currency");
    private String symbols = properties().getProperty("currency");
    private String gif_apiKey = properties().getProperty("apiKey");
    private String gif_rating = properties().getProperty("gif_rating");
    private String gif_tag_rich = properties().getProperty("gif_tag_rich");
    private String gif_tag_broke = properties().getProperty("gif_tag_broke");
    private LinkedHashMap expected;
    private LinkedHashMap actual;
    @MockBean
    GIFservice giFserviceMock = mock(GIFservice.class);

    private static ResponseForTest responseForTest = new ResponseForTest();

    @ParameterizedTest
    @MethodSource("positiveExpected")
    public void methodPositive(ENUMResponses expected_param, ENUMResponses actual_param) {
        Mockito.when(giFserviceMock.getData(gif_apiKey, gif_tag_broke, gif_rating))
                .thenReturn(responseForTest.getResponse(actual_param));
        expected = responseForTest.getResponse(expected_param);
        actual = (LinkedHashMap) giFserviceMock.getData(gif_apiKey, gif_tag_broke, gif_rating);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> positiveExpected() {
        return Stream.of(
                arguments(ENUMResponses.GIF_BROKE, ENUMResponses.GIF_BROKE),
                arguments(ENUMResponses.GIF_RICH, ENUMResponses.GIF_RICH)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeExpected")
    public void methodNegative(ENUMResponses expected_param, ENUMResponses actual_param) {
        Mockito.when(giFserviceMock.getData(gif_apiKey, gif_tag_broke, gif_rating))
                .thenReturn(responseForTest.getResponse(actual_param));
        expected = responseForTest.getResponse(expected_param);
        actual = (LinkedHashMap) giFserviceMock.getData(gif_apiKey, gif_tag_broke, gif_rating);
        assertNotEquals(expected, actual);
    }

    static Stream<Arguments> negativeExpected() {
        return Stream.of(
                arguments(ENUMResponses.GIF_BROKE, ENUMResponses.GIF_RICH),
                arguments(ENUMResponses.GIF_RICH, ENUMResponses.GIF_BROKE)
        );
    }
}
