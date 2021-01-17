package kondratov.spring_feign.feign;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import static kondratov.spring_feign.feign.PropertyService.properties;
import static org.mockito.Mockito.mock;

public class AppControllerMockbeanTest {

    private String type_request_toDay = "latest.json";
    private AppController appController = new AppController();
    private String type_request_yesterday = "historical/" + new DateTime().getTimeTwoDaysAgo() + ".json";
    private String app_id = properties().getProperty("id_currency");
    private String symbols = properties().getProperty("currency");
    private String gif_apiKey = properties().getProperty("apiKey");
    private String gif_rating = properties().getProperty("gif_rating");
    private String gif_tag_rich = properties().getProperty("gif_tag_rich");
    private String gif_tag_broke = properties().getProperty("gif_tag_broke");
    private String expected;
    private ResponseForTest responseForTest;
    private APIcash apIcash = new APIcash();


    @MockBean
    GIFservice giFserviceMock = mock(GIFservice.class);
    CourseService courseServiceMock = mock(CourseService.class);


    public String appcontrollerTest(double course_toDay, double course_yesterDay) {
        responseForTest = new ResponseForTest(course_toDay, course_yesterDay);

        Mockito.when(courseServiceMock.getData(type_request_toDay, app_id, symbols))
                .thenReturn(responseForTest.getResponse(ENUMResponses.COURSE_TD));
        Mockito.when(courseServiceMock.getData(type_request_yesterday, app_id, symbols))
                .thenReturn(responseForTest.getResponse(ENUMResponses.COURSE_YTD));
        Mockito.when(giFserviceMock.getData(gif_apiKey, gif_tag_rich, gif_rating))
                .thenReturn(responseForTest.getResponse(ENUMResponses.GIF_RICH));
        Mockito.when(giFserviceMock.getData(gif_apiKey, gif_tag_broke, gif_rating))
                .thenReturn(responseForTest.getResponse(ENUMResponses.GIF_BROKE));

//      double  currencyCourses_toDay = (double) ((LinkedHashMap) lhmcourseTD.get("rates")).get(symbols);
//      double  currencyCourses_yesterday = (double) ((LinkedHashMap) lhmcourseYTD.get("rates")).get(symbols);

        expected = appController.getData(giFserviceMock, courseServiceMock, apIcash);
        return expected;
    }


}