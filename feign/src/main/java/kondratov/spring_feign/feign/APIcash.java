package kondratov.spring_feign.feign;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

import static kondratov.spring_feign.feign.CurrencyCourse.setCoursTD;
import static kondratov.spring_feign.feign.CurrencyCourse.setCouursYTD;
import static kondratov.spring_feign.feign.PropertyService.properties;


enum CourseTime {
    TODAY, YESTERDAY, TWO_DAYS_AGO;
}

@Configuration                                       //properties
@PropertySource("classpath:/app.properties")         //properties


@RestController

public class APIcash {


    private DateTime dateTime = new DateTime();
    private String type_request;
    private String app_id = properties().getProperty("id_currency");
    private String symbols = properties().getProperty("currency");
    private LinkedHashMap course;
    private double currencyCourses;


    private double getCourseLocal(CourseTime courseTime, CourseService courseService) {


        switch (courseTime) {
            case TODAY:
                type_request = "latest.json";
                course = (LinkedHashMap) courseService.getData(type_request, app_id, symbols);
                break;
            case TWO_DAYS_AGO:
                type_request = "historical/" + dateTime.getTimeTwoDaysAgo() + ".json";
                course = (LinkedHashMap) courseService.getData(type_request, app_id, symbols);
              //  System.out.println((double) ((LinkedHashMap) course.get("rates")).get(symbols) + "0000");
                break;
        }
        currencyCourses = (double) ((LinkedHashMap) course.get("rates")).get(symbols);
        return currencyCourses;
    }

    public boolean CourseUP(CourseService courseService) {

        double toDay = getCourseLocal(CourseTime.TODAY, courseService);
        setCoursTD(Double.toString(toDay));
       // System.out.println(toDay + "!!!!!!!!!!!!!");
        double twoDaysAgo = getCourseLocal(CourseTime.TWO_DAYS_AGO, courseService);
       // System.out.println(twoDaysAgo + "!!!!!!!!!!!!!");
        setCouursYTD(Double.toString(twoDaysAgo));

        if (toDay > twoDaysAgo) {
            return true;
        } else {
            return false;
        }
    }
}

