package com.recipe.universe.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.universe.global.security.authorization.WebAuthorizationDelegator;
import com.recipe.universe.global.security.filter.JwtAuthenticationFilter;
import com.recipe.universe.domain.user.oauth2.handler.OidcAuthenticationSuccessHandler;
import com.recipe.universe.domain.user.oauth2.service.CustomOidcService;
import com.recipe.universe.domain.user.role.service.RoleService;
import com.recipe.universe.global.security.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OidcAuthenticationSuccessHandler oidcAuthenticationSuccessHandler;
    private final CustomOidcService customOidcService;
    private final RoleService roleService;
    private final ObjectMapper objectMapper;
    private final WebAuthorizationDelegator webAuthorizationDelegator;

    @Order(2)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        authroize (5.7이전버전)
        authorizeRequest (6.2 이전버전)
        authorizeHttpRequests (6.2.1 이상버전)
         */
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // legacy, we will delete soon
                        .requestMatchers("/oauth2/**").permitAll()
                        .requestMatchers("/login/**", "/logout").permitAll()
//                        .requestMatchers("/docs/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/recipe/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ratings/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/ing/file/**").permitAll()
                        .anyRequest().access((webAuthorizationDelegator::decide))//.authenticated()
                )
                .oauth2Login(config -> config
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .oidcUserService(customOidcService))
                        .successHandler(oidcAuthenticationSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/api/ur/logout"))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(config->config.authenticationEntryPoint(entryPoint()));

        return http.build();
    }

    @Order(1)
    @Profile("prod")
    @Bean
    public SecurityFilterChain legacyFilterChain(HttpSecurity http) throws Exception {
        /*
        authroize (5.7이전버전)
        authorizeRequest (6.2 이전버전)
        authorizeHttpRequests (6.2.1 이상버전)
         */
        http
                .securityMatcher("/docs/**", "/login")
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/docs/**").authenticated()
                );
        return http.build();
    }


    @Bean
    public RoleHierarchy roleHierarchy(){
        List<String> expressions = roleService.getRoleHierarchyExpression();
        StringBuilder sb = new StringBuilder();
        for(String exp : expressions){
            sb.append(exp).append("\n");
        }
        return RoleHierarchyImpl.fromHierarchy(sb.toString());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:7071");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CustomAuthenticationEntryPoint entryPoint(){
        return new CustomAuthenticationEntryPoint(objectMapper, "/oauth2/authorization/{social-service}");
    }
}
