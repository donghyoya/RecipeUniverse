package com.recipe.universe.global.config;

import com.recipe.universe.domain.user.service.authentication.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityUserService securityUserService;

    @Autowired
    public SecurityConfig(SecurityUserService securityUserService){
        this.securityUserService = securityUserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(
                        csrf -> csrf
                                .disable()
                );
        /*
        authroize (5.7이전버전)
        authorizeRequest (6.2 이전버전)
        authorizeHttpRequests (6.2.1 이상버전)
         */
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/ur/login", "/api/ur/logout").permitAll()
                        .requestMatchers("/api/ur/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/api/ur/login"))
                .logout(logout -> logout
                        .logoutUrl("/api/ur/logout"))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
