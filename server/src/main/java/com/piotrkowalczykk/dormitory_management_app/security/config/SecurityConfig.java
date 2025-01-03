package com.piotrkowalczykk.dormitory_management_app.security.config;

import com.piotrkowalczykk.dormitory_management_app.security.utils.JsonWebToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JsonWebToken jsonWebToken;
    private final JWTAuthEntryPoint jwtAuthEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JWTAuthEntryPoint jwtAuthEntryPoint, JsonWebToken jsonWebToken) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.jsonWebToken = jsonWebToken;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/feed/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.addFilterBefore(jwtAuthFilter(jsonWebToken, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthFilter jwtAuthFilter(JsonWebToken jsonWebToken, CustomUserDetailsService customUserDetailsService){
        return new JWTAuthFilter(jsonWebToken, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
