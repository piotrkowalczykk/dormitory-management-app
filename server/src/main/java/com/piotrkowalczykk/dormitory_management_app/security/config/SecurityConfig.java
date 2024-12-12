package com.piotrkowalczykk.dormitory_management_app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth/register").permitAll().anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
