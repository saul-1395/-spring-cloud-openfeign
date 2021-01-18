package kondratov.spring_feign.feign;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static kondratov.spring_feign.feign.PropertyService.properties;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

enum TestAPI {
    GIF_API, TD_COURSE_API, YTD_COURSE_API
}

public class APITests {
    private String type_request_toDay = "latest.json";

    private String type_request_yesterday = "historical/" + new DateTime().getTimeTwoDaysAgo() + ".json";
    private String app_id = properties().getProperty("id_currency");
    private String symbols = properties().getProperty("currency");
    private String gif_apiKey = properties().getProperty("apiKey");
    private String gif_rating = properties().getProperty("gif_rating");
    private String gif_tag_rich = properties().getProperty("gif_tag_rich");
    private String gif_tag_broke = properties().getProperty("gif_tag_broke");


    RestTemplate restTemplate;
    ResponseEntity response;
    private String url;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().bindAddress(url).port(8443));


    @Before
    public void setup() throws Exception {
        restTemplate = new RestTemplate();
        response = null;
    }

    @ParameterizedTest
    @MethodSource("statusOK")
    public void methodAPItest(TestAPI testAPI) {

        RestTemplate restTemplate = new RestTemplate();
        url = getAPI(testAPI);
        response = restTemplate.getForEntity(url, String.class);

        // assertThat("Verify Response Body", response.getBody().contains("mappings"));
        assertThat("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));
    }

    static Stream<Arguments> statusOK() {
        return Stream.of(
                arguments(TestAPI.GIF_API),
                arguments(TestAPI.TD_COURSE_API),
                arguments(TestAPI.YTD_COURSE_API)
        );
    }

    private String getAPI(TestAPI api) {

        switch (api) {
            case GIF_API:
                // "https://api.giphy.com/v1/gifs/random?api_key= YjMBpwuaouTnbKjk18LH9rM2uOrNivuk &tag= rich &rating= g"
                url = "https://api.giphy.com/v1/gifs/random?api_key=" + gif_apiKey +
                        "&tag=" + gif_tag_rich + "&rating=" + gif_rating;
                System.out.println(url);
                break;
            case TD_COURSE_API:
                //https://openexchangerates.org/ api/ latest.json  ?app_id=185161acba574512b653852e440401b8&symbols=RUB
                url = "https://openexchangerates.org/ api/" + type_request_toDay + "?app_id=" + app_id + "&symbols=" + symbols;
                break;
            case YTD_COURSE_API:
                //https://openexchangerates.org/ api/ historical/2021-01-07 .json  ?app_id=185161acba574512b653852e440401b8&symbols=RUB
                url = "https://openexchangerates.org/ api/" + type_request_yesterday + "?app_id=" + app_id + "&symbols=" + symbols;
                break;
        }
        return url;
    }
}