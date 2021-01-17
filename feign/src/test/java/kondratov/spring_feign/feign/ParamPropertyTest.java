package kondratov.spring_feign.feign;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static kondratov.spring_feign.feign.PropertyService.properties;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParamPropertyTest {


    private String actual;


    @ParameterizedTest
    @MethodSource("positiveExpected")
    public void method1(String expected, String param) {
        actual = properties().getProperty(param);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> positiveExpected() {
        return Stream.of(
                arguments("RUB", "currency"),
                arguments("185161acba574512b653852e440401b8", "id_currency"),
                arguments("YjMBpwuaouTnbKjk18LH9rM2uOrNivuk", "apiKey"),
                arguments("g", "gif_rating"),
                arguments("rich", "gif_tag_rich"),
                arguments("broke", "gif_tag_broke")
        );
    }
}