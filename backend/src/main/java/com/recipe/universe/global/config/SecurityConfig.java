package com.recipe.universe.global.config;

import com.recipe.universe.domain.user.jwt.service.filter.JwtAuthenticationFilter;
import com.recipe.universe.domain.user.oauth2.converter.OidcUserConverter;
import com.recipe.universe.domain.user.oauth2.handler.OidcAuthenticationSuccessHandler;
import com.recipe.universe.domain.user.oauth2.service.CustomOidcService;
import com.recipe.universe.domain.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OidcAuthenticationSuccessHandler oidcAuthenticationSuccessHandler;
    private final List<OidcUserConverter> converters;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        authroize (5.7이전버전)
        authorizeRequest (6.2 이전버전)
        authorizeHttpRequests (6.2.1 이상버전)
         */
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // legacy, we will delete soon
                        .requestMatchers("/oauth2/**").permitAll()
                        .requestMatchers("/login/**", "/logout").permitAll()
                        .requestMatchers("/docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(config -> config
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .oidcUserService(oidcUserService()))
                        .successHandler(oidcAuthenticationSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/api/ur/logout"))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public OidcUserService oidcUserService(){
        return new CustomOidcService(userService, userDetailsService, converters);
    }
}
