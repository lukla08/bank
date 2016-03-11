package pl.training.bank.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.training.bank.dto.CustomObjectMapper;

import java.util.List;

@ComponentScan("pl.training.bank.rest")
@EnableWebMvc
@Configuration
public class Web extends WebMvcConfigurerAdapter {

    @Bean
    public CustomObjectMapper objectMapper() {
        return new CustomObjectMapper();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.parallelStream()
                .filter(converter -> converter instanceof AbstractJackson2HttpMessageConverter)
                .map(converter -> ((AbstractJackson2HttpMessageConverter) converter).getObjectMapper())
                .forEach(objectMapper -> {
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                });
    }

}
