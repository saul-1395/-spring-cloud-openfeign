package kondratov.spring_feign.feign;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;
import static kondratov.spring_feign.feign.PropertyService.*;
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
        return (double) ((LinkedHashMap) course.get("rates")).get("RUB");
    }

    public boolean CourseUP(CashCourse cashCourse) {

        double toDay = getCourseLocal(CourseTime.TODAY, cashCourse);
        double twoDaysAgo = getCourseLocal(CourseTime.TWO_DAYS_AGO, cashCourse);


        if (toDay > twoDaysAgo) {
            return true;
        } else {
            return false;
        }
    }
}

