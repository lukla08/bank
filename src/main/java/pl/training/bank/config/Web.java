package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.training.bank.dto.CustomObjectMapper;

@ComponentScan("pl.training.bank.rest")
@EnableWebMvc
@Configuration
public class Web {

    @Bean
    public CustomObjectMapper objectMapper() {
        return new CustomObjectMapper();
    }

}
