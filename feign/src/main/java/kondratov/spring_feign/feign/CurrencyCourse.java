package kondratov.spring_feign.feign;
import static kondratov.spring_feign.feign.PropertyService.properties;

public class CurrencyCourse {
    private static String currency = properties().getProperty("currency");
    private static String coursTD;
    private static String coursYTD;
    private static CurrencyCourse currencyCourse = null;

    public CurrencyCourse() {
    }

    public static String getCurrency() {
        return currency;
    }

    public static CurrencyCourse getCurrencyCourse() {
        if(currencyCourse == null){
            return currencyCourse  = new CurrencyCourse();
        }
        return currencyCourse;
    }



    public static String getCoursTD() {
        return coursTD;
    }

    public static void setCoursTD(String coursTD) {
        CurrencyCourse.coursTD = coursTD;
    }

    public static String getCouursYTD() {
        return coursYTD;
    }

    public static void setCouursYTD(String couursYTD) {
        CurrencyCourse.coursYTD = couursYTD;
    }
}
