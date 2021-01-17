package kondratov.spring_feign.feign;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static kondratov.spring_feign.feign.PropertyService.properties;

public class ParamAppConTest {

    private String actual;
    private AppControllerMockbeanTest appControllerMockbeanTest = new AppControllerMockbeanTest();

    @ParameterizedTest
    @MethodSource("positiveExpected")
    public void method1(String expected, double param1, double param2) {
        actual = appControllerMockbeanTest.appcontrollerTest(param1, param2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("negativeExpected")
    public void method2(String expected, double param1, double param2) {
        actual = appControllerMockbeanTest.appcontrollerTest(param1, param2);
        assertNotEquals(expected, actual);
    }

    static Stream<Arguments> positiveExpected() {
        return Stream.of(
                arguments(properties().getProperty("gif_tag_rich"), 40, 20),
                arguments(properties().getProperty("gif_tag_broke"), 40, 60),
                arguments(properties().getProperty("gif_tag_broke"), 40, 40)
        );
    }

    static Stream<Arguments> negativeExpected() {
        return Stream.of(
                arguments(properties().getProperty("gif_tag_broke"), 40, 20),
                arguments(properties().getProperty("gif_tag_rich"), 40, 60),
                arguments(properties().getProperty("gif_tag_rich"), 40, 40)
        );
    }
}
