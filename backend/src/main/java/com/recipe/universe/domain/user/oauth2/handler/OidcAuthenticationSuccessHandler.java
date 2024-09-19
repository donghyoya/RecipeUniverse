package com.recipe.universe.domain.user.oauth2.handler;

import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OidcAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenService jwtTokenService;
    private String redirectUrl = "http://localhost:3000/auth/redirect";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = jwtTokenService.generateToken(authentication);
        // redirectUrl로 ACCESS TOKEN 전달
        String uri = UriComponentsBuilder.fromHttpUrl(redirectUrl).queryParam("token", token).build().encode().toUriString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }
}
