package com.rocs.osd.utils.security.configuration;

import com.rocs.osd.utils.security.constant.SecurityConstant;
import com.rocs.osd.utils.security.jwt.filter.authentication.access.denied.JwtAccessDeniedHandler;
import com.rocs.osd.utils.security.jwt.filter.authentication.forbidden.AuthenticationEntryPoint;
import com.rocs.osd.utils.security.jwt.filter.authorization.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
/**
 * This class provides the security configuration for the application, setting up authentication, authorization,
 * and Cross-Origin Resource Sharing that allow or restrict access to the application’s resources from different origins (domains).
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private JwtAuthorizationFilter jwtAuthorizationFilter;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationConfiguration authConfiguration;

    /**
     * Constructor to inject dependencies.
     *
     * @param jwtAuthorizationFilter filter the JWT token authorization
     * @param jwtAccessDeniedHandler handle the access denied errors
     * @param authenticationEntryPoint entry point for authentication failures
     * @param userDetailsService load the user details
     * @param bCryptPasswordEncoder password encoder (BCrypt)
     * @param authConfiguration configuration for authentication management
     */
    @Autowired
    public SecurityConfiguration(JwtAuthorizationFilter jwtAuthorizationFilter,
                                 JwtAccessDeniedHandler jwtAccessDeniedHandler,
                                 AuthenticationEntryPoint authenticationEntryPoint,
                                 @Qualifier("userDetailsService") UserDetailsService userDetailsService,
                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                 AuthenticationConfiguration authConfiguration) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authConfiguration = authConfiguration;
    }

    /**
     * Configures the AuthenticationManager with a user details service and password encoder.
     *
     * @param auth the builder for the authentication manager
     * @throws Exception if an error occurs during configuration
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Provides the AuthenticationManager bean used for managing authentication.
     *
     * @return the AuthenticationManager bean
     * @throws Exception if an error occurs during authentication setup
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    /**
     * Configures CORS settings, allowing specific domains and headers.
     *
     * @return the CORS configuration source
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of(
                "Access-Control-Allow-Headers",
                "Authorization",
                "x-xsrf-token",
                "Access-Control-Allow-Headers",
                "Origin",
                "Accept",
                "X-Requested-With",
                "Content-Type",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Jwt-Token",
                "Uid"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Configures security filters, session management, and request authorizations.
     *
     * @param http the security configuration for HTTP requests
     * @return the configured security filter chain
     * @throws Exception if an error occurs during security setup
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> corsConfigurationSource())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(SecurityConstant.PUBLIC_URLS).permitAll().anyRequest().authenticated())
                .exceptionHandling((e) -> {
                    e.authenticationEntryPoint(authenticationEntryPoint);
                    e.accessDeniedHandler(jwtAccessDeniedHandler);})
                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class);
        return http.build();
    }
}
