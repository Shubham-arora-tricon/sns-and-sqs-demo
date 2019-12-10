package com.triconinfotech.aws.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * CORS Configuration class.
 * @author shubham.arora
 */
@Configuration
public class CorsConfiguration {

    /**
     * The.
     * @return new {@link WebMvcConfigurer}
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}