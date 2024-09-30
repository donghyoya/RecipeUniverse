package com.recipe.universe.global.config;

import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret:0ed89e38998c31d591261887d37e2148c8dea714330af0febac9b9d22e62517c}") private String jwtSecretKey;

    @Bean
    public JwtTokenService jwtTokenService(){
        return new JwtTokenService(jwtSecretKey);
    }
}
