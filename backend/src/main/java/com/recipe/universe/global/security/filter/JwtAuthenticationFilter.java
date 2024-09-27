package com.recipe.universe.global.security.filter;

import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final SecurityContextLogoutHandler securityContextLogoutHandler;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
        this.securityContextLogoutHandler = new SecurityContextLogoutHandler();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !jwtTokenService.isBearer(authHeader)){
            chain.doFilter(request, response);
            return;
        }

        /* 인증 처리 */
        String token = jwtTokenService.getToken(authHeader);
        Claims claims = jwtTokenService.validateToken(token);

        Boolean isRefresh = claims.get("isRefresh", Boolean.class);
        if(isRefresh != null && isRefresh.equals(true)){
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                null
        );

        // Context에 저장하기
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.getContextHolderStrategy().setContext(context);

        chain.doFilter(request,response);

        // JWT 토큰이므로 clearContext처리
        securityContextLogoutHandler.logout(request, response, authentication);

        SecurityContextHolder.clearContext();
    }
}
