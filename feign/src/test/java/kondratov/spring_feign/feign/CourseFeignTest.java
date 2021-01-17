package kondratov.spring_feign.feign;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static kondratov.spring_feign.feign.PropertyService.properties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class CourseFeignTest {
    private String type_request_toDay = "latest.json";
    private String type_request_yesterday = "historical/" + new DateTime().getTimeTwoDaysAgo() + ".json";
    private String app_id = properties().getProperty("id_currency");
    private String symbols = properties().getProperty("currency");
    private LinkedHashMap expected;
    private LinkedHashMap actual;


    @MockBean
    CourseService courseServiceMock = mock(CourseService.class);


    @ParameterizedTest
    @MethodSource("positiveExpected")
    public void methodPositive(
            ENUMResponses expected_param_TD, ENUMResponses actual_param_TD,
            ENUMResponses expected_param_YTD, ENUMResponses actual_param_YTD,
            double courseTD, double courseYTD) {

        ResponseForTest responseForTest = new ResponseForTest(courseTD, courseYTD);
        Mockito.when(courseServiceMock.getData(type_request_toDay, app_id, symbols))
                .thenReturn(responseForTest.getResponse(actual_param_TD));
        Mockito.when(courseServiceMock.getData(type_request_yesterday, app_id, symbols))
                .thenReturn(responseForTest.getResponse(actual_param_YTD));

        expected = responseForTest.getResponse(expected_param_TD);
        actual = (LinkedHashMap) courseServiceMock.getData(type_request_toDay, app_id, symbols);

        assertEquals(expected, actual);

        expected = responseForTest.getResponse(expected_param_YTD);
        actual = (LinkedHashMap) courseServiceMock.getData(type_request_yesterday, app_id, symbols);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> positiveExpected() {
        return Stream.of(
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, ENUMResponses.COURSE_YTD, 40d, 60d)
                );
    }

    @ParameterizedTest
    @MethodSource("negativeExpected")
    public void methodNegative(
            ENUMResponses expected_param_TD, ENUMResponses actual_param_TD,
            ENUMResponses expected_param_YTD, ENUMResponses actual_param_YTD,
            double courseTD, double courseYTD) {

        ResponseForTest responseForTest = new ResponseForTest(courseTD, courseYTD);
        Mockito.when(courseServiceMock.getData(type_request_toDay, app_id, symbols))
                .thenReturn(responseForTest.getResponse(actual_param_TD));
        Mockito.when(courseServiceMock.getData(type_request_yesterday, app_id, symbols))
                .thenReturn(responseForTest.getResponse(actual_param_YTD));

        expected = responseForTest.getResponse(expected_param_TD);
        actual = (LinkedHashMap) courseServiceMock.getData(type_request_toDay, app_id, symbols);

        assertNotEquals(expected, actual);

        expected = responseForTest.getResponse(expected_param_YTD);
        actual = (LinkedHashMap) courseServiceMock.getData(type_request_yesterday, app_id, symbols);

        assertNotEquals(expected, actual);
    }

    static Stream<Arguments> negativeExpected() {
        return Stream.of(
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, ENUMResponses.COURSE_YTD, ENUMResponses.COURSE_TD, 40d, 60d)
        );
    }
}
