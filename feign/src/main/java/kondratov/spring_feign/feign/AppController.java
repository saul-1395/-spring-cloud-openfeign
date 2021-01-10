package kondratov.spring_feign.feign;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

import static kondratov.spring_feign.feign.PropertyService.properties;
@RestController
public class AppController {

    private String tag;
    private boolean courseUP;
    private LinkedHashMap response = new LinkedHashMap();
    private String GIF_URL;
    private String apiKey = properties().getProperty("apiKey");


    public String getData(GIFservice giFservice, CashCourse cashCourse, APIcash apIcash) {

        courseUP = apIcash.CourseUP(cashCourse);
        if (courseUP) {
            tag = "rich";
        } else {
            tag = "broke";
        }

        response = LinkedHashMap.class.cast(giFservice.getData(apiKey, tag, "g"));
        GIF_URL = (String) ((LinkedHashMap) (((LinkedHashMap) (((LinkedHashMap) response.get("data")).get("images"))).get("original"))).get("url");
        System.out.println(
                GIF_URL
        );

        return GIF_URL;
    }

}
