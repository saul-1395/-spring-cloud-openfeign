package kondratov.spring_feign.feign;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class PropertyService {

    public PropertyService() {

    }


    private static AnnotationConfigApplicationContext context = null;

    public static ConfigurableEnvironment properties() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext();
            context.register(APIcash.class);
            context.refresh();
        }

        return context.getEnvironment();
    }
}
