package kondratov.spring_feign.feign;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;
import static kondratov.spring_feign.feign.PropertyService.*;
import static kondratov.spring_feign.feign.CurrencyCourse.*;
import java.util.LinkedHashMap;


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
    private Object response;
    private double currencyCourses;
   // private String webAdres = "openexchangerates.org";


    private double getCourseLocal(CourseTime courseTime, CashCourse cashCourse) {



        switch (courseTime) {
            case TODAY:

                type_request = "latest.json";

                response = cashCourse.getData(type_request, app_id, symbols);
                course = LinkedHashMap.class.cast(response);

                break;
            case TWO_DAYS_AGO:

                type_request = "historical/" + dateTime.getTimeTwoDaysAgo() + ".json";

                response = cashCourse.getData( type_request, app_id, symbols);
                course = LinkedHashMap.class.cast(response);
                break;
        }

        currencyCourses = (double) ((LinkedHashMap) course.get("rates")).get(symbols);
        return currencyCourses;
    }

    public boolean CourseUP(CashCourse cashCourse) {

        double toDay = getCourseLocal(CourseTime.TODAY, cashCourse);
        setCoursTD(Double.toString(toDay));
        double twoDaysAgo = getCourseLocal(CourseTime.TWO_DAYS_AGO, cashCourse);
        setCouursYTD(Double.toString(twoDaysAgo));

        if (toDay > twoDaysAgo) {
            return true;
        } else {
            return false;
        }
    }
}

