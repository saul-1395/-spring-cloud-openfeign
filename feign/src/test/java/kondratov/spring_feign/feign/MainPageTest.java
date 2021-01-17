package kondratov.spring_feign.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

public class MainPageTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("http://localhost:8082/formhtml"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Financial forecast:")));
    }
}
