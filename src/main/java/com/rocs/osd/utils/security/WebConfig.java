package com.rocs.osd.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  WebConfig is a configuration class that sets up CORS (Cross-Origin Resource Sharing) settings for the application
 * This allows specific origins to access the server's resources.
 */

@Configuration
public class WebConfig {

    /**
     * Configures (Cross-Origin Resource Sharing) CORS settings for the entire application.
     *
     * @return a WebMvcConfigurer instance
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
