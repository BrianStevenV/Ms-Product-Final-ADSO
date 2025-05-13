package com.example.demo.config.security;

import com.example.demo.config.security.jwt.JwtEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.FREE_GET_FEEDBACK_PRODUCT;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.GET_ALL_PRODUCTS_BY_PROVIDER_ID;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.GET_PRODUCT_BY_ID;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.PATCH_ADD_PRODUCT_QUANTITY;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.POST_DISABLE_PRODUCT;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.POST_PRODUCT_ATTRIBUTE_CREATE;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.POST_PRODUCT_CREATE;
import static com.example.demo.config.security.utils.ConstantsSecurity.PROVIDER_ROLE;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( req -> req
//                        .requestMatchers(FREE_GET_FEEDBACK_PRODUCT).permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                POST_PRODUCT_CREATE,
                                POST_DISABLE_PRODUCT,
                                POST_PRODUCT_ATTRIBUTE_CREATE,
                                FREE_GET_FEEDBACK_PRODUCT
                        ).hasAuthority(PROVIDER_ROLE)
                        .requestMatchers(
                                HttpMethod.GET,
                                GET_ALL_PRODUCTS_BY_PROVIDER_ID,
                                GET_PRODUCT_BY_ID,
                                GET_ALL_CATEGORIES_ATTRIBUTES_ACCORDING_TO_CATEGORY_ID
                        ).hasAuthority(PROVIDER_ROLE)
                        .requestMatchers(
                                HttpMethod.PATCH,
                                PATCH_ADD_PRODUCT_QUANTITY
                        ).hasAuthority(PROVIDER_ROLE)
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        config.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
