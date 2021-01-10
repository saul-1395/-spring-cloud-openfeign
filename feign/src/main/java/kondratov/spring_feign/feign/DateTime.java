package kondratov.spring_feign.feign;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private String getTime(long time) {


        DateFormat simple = new SimpleDateFormat("YYYY-MM-DD");

        Date result = new Date(time);

        String date = simple.format(result);

        System.out.println(date);

        return date;

    }


    public String getTimeToDay() {

        long now = System.currentTimeMillis();

        return getTime(now);

    }

    public String getTimeYestoday() {

        long yesturday = System.currentTimeMillis() - 24 * 3600 * 1000;

        return getTime(yesturday);
    }

    public String getTimeTwoDaysAgo() {

        long yesturday = System.currentTimeMillis() - 24 * 3600 * 1000 * 2;

        return getTime(yesturday);
    }


}
