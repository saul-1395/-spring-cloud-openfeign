package kondratov.spring_feign.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PropertyServiceTest {

    @Autowired
    PropertyService propertyService;

    @Test
    void propperties(){
        assertEquals("RUB", propertyService.properties().getProperty("val"));
    }


}