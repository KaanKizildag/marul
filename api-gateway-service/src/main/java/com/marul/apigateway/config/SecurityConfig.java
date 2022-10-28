package com.marul.apigateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    protected SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .authorizeExchange()
                .anyExchange().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(List.of("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


}

