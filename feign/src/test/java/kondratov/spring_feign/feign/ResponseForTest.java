package kondratov.spring_feign.feign;

import java.util.LinkedHashMap;

import static kondratov.spring_feign.feign.PropertyService.properties;

enum ENUMResponses {GIF_RICH, GIF_BROKE, COURSE_TD, COURSE_YTD}

public class ResponseForTest {

    private String symbols = properties().getProperty("currency");
    private String gif_tag_rich = properties().getProperty("gif_tag_rich");
    private String gif_tag_broke = properties().getProperty("gif_tag_broke");
    private double courseTD;
    private double courseYTD;

    public ResponseForTest(double courseTD, double courseYTD) {
        this.courseTD = courseTD;
        this.courseYTD = courseYTD;
    }

    public ResponseForTest() {
        this.courseTD = courseTD;
        this.courseYTD = courseYTD;
    }

    public LinkedHashMap getResponse(ENUMResponses response_type) {
        LinkedHashMap response = new LinkedHashMap();

        switch (response_type) {
            case GIF_RICH:
                response = new LinkedHashMap() {{
                    put("data", new LinkedHashMap() {{
                        put("images", new LinkedHashMap() {{
                            put("original", new LinkedHashMap() {{
                                put("url", gif_tag_rich);
                            }});
                        }});
                    }});
                }};
                break;
            case GIF_BROKE:
                response = new LinkedHashMap() {{
                    put("data", new LinkedHashMap() {{
                        put("images", new LinkedHashMap() {{
                            put("original", new LinkedHashMap() {{
                                put("url", gif_tag_broke);
                            }});
                        }});
                    }});
                }};
                break;
            case COURSE_TD:
                response = new LinkedHashMap() {{
                    put("rates", new LinkedHashMap() {{
                        put(symbols, courseTD);
                    }});
                }};
                break;
            case COURSE_YTD:
                response = new LinkedHashMap() {{
                    put("rates", new LinkedHashMap() {{
                        put(symbols, courseYTD);
                    }});
                }};
                break;
        }

        return response;
    }
}
