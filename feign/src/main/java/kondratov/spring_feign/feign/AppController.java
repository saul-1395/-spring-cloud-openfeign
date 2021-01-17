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
    private String rating = properties().getProperty("gif_rating");


    public String getData(GIFservice giFservice, CourseService courseService, APIcash apIcash) {

        courseUP = apIcash.CourseUP(courseService);
        if (courseUP) {
            tag = properties().getProperty("gif_tag_rich");

        } else {
            tag = properties().getProperty("gif_tag_broke");
        }

        response = (LinkedHashMap) (giFservice.getData(apiKey, tag, rating));
        GIF_URL = (String) ((LinkedHashMap) (((LinkedHashMap) (((LinkedHashMap) response.get("data")).get("images"))).get("original"))).get("url");
        System.out.println(
                GIF_URL
        );

        return GIF_URL;
    }

}
