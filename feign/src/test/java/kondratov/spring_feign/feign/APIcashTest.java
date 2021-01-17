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

class APIcashTest {
    private String type_request_toDay = "latest.json";
    private String type_request_yesterday = "historical/" + new DateTime().getTimeTwoDaysAgo() + ".json";
    private String app_id = properties().getProperty("id_currency");
    private String symbols = properties().getProperty("currency");
    private LinkedHashMap getCourse;
    private double course;
    private APIcash apIcashACT = new APIcash();
    private ResponseForTest responseForTest;


    @MockBean
    CourseService courseServiceMock = mock(CourseService.class);


    @ParameterizedTest
    @MethodSource("positiveExpected")
    public void methodPositive(ENUMResponses param_TD, ENUMResponses param_YTD,
                               double TD, double YTD) {

        responseForTest = new ResponseForTest(TD, YTD);

//        double actualTD = getCourse(type_request_toDay, ENUMResponses.COURSE_TD);
//        double actualYTD = getCourse(type_request_yesterday, ENUMResponses.COURSE_YTD);
        Mockito.when(courseServiceMock.getData(type_request_toDay, app_id, symbols))
                .thenReturn(responseForTest.getResponse(param_TD));
        Mockito.when(courseServiceMock.getData(type_request_yesterday, app_id, symbols))
                .thenReturn(responseForTest.getResponse(param_YTD));


        boolean resultACT = apIcashACT.CourseUP(courseServiceMock);
        boolean resultExp;
        if (TD > YTD) {
            resultExp = true;
        } else {
            resultExp = false;
        }
        assertEquals(resultExp, resultACT);
    }

    @ParameterizedTest
    @MethodSource("negativeExpected")
    public void methodNegative( ENUMResponses param_YTD, ENUMResponses param_TD,
                               double TD, double YTD) {

        responseForTest = new ResponseForTest(TD, YTD);

//        double actualTD = getCourse(type_request_toDay, ENUMResponses.COURSE_TD);
//        double actualYTD = getCourse(type_request_yesterday, ENUMResponses.COURSE_YTD);
        Mockito.when(courseServiceMock.getData(type_request_toDay, app_id, symbols))
                .thenReturn(responseForTest.getResponse(param_TD));
        Mockito.when(courseServiceMock.getData(type_request_yesterday, app_id, symbols))
                .thenReturn(responseForTest.getResponse(param_YTD));


        boolean resultACT = apIcashACT.CourseUP(courseServiceMock);
        boolean resultExp;
        if (TD > YTD) {
            resultExp = true;
        } else {
            resultExp = false;
        }
        assertNotEquals(resultExp, resultACT);
    }

    static Stream<Arguments> positiveExpected() {
        return Stream.of(
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 80d, 60d),
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 70d, 50d),
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 50d, 60d),
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 50d, 50d)
        );
    }

    static Stream<Arguments> negativeExpected() {
        return Stream.of(
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 80d, 60d),
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 70d, 50d),
                arguments(ENUMResponses.COURSE_TD, ENUMResponses.COURSE_YTD, 50d, 60d)
        );
    }

//    private double getCourse(String type_request, ENUMResponses type_response) {
//        Mockito.when(courseServiceMock.getData(type_request, app_id, symbols))
//                .thenReturn(responseForTest.getResponse(type_response));
//        getCourse = (LinkedHashMap) courseServiceMock.getData(type_request, app_id, symbols);
//        course = (double) ((LinkedHashMap) getCourse.get("rates")).get(symbols);
//        return course;
//    }


}
