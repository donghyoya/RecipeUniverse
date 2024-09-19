package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.user.controller.form.UserRegistForm;
import com.recipe.universe.domain.user.jwt.dto.JwtTokenDto;
import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.user.service.UserService;
import com.recipe.universe.domain.user.authentication.CustomAuthenticationProvider;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "레거시", description = "OAUTH2(소셜로그인)을 사용하지 않는 구버전의 회원가입 및 로그인")
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final CustomAuthenticationProvider provider;

    @PostMapping("/regist")
    public String regist(@RequestBody UserRegistForm form){
        Long save = userService.save(form.getId(), form.getPassword());
        return "Login Success";
    }

    @GetMapping("/login")
    public JwtTokenDto basicAuthentication(@RequestHeader("Authorization") String authHeader){
        // TokenType이 Basic인지 확인
        if(!jwtTokenService.isBasic(authHeader)){
            throw new RuntimeException();
        }

        // Token가져오기
        String token = jwtTokenService.getToken(authHeader);
        String[] decodeTokens = jwtTokenService.decodeBasicToken(token);
        if(decodeTokens.length != 2){
            throw new RuntimeException();
        }
        String username = decodeTokens[0];
        String password = decodeTokens[1];

        // 검증
        Authentication authenticate = provider.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        JwtTokenDto jwtTokenDto = jwtTokenService.generateToken(authenticate.getName());
        return jwtTokenDto;
    }

    @GetMapping("/refresh")
    public JwtTokenDto refresh(@RequestHeader("Authorization") String authHeader){
        if(!jwtTokenService.isBearer(authHeader)){
            throw new RuntimeException();
        }

        String token = jwtTokenService.getToken(authHeader);
        Claims claims = jwtTokenService.validateToken(token);
        if(!claims.get("isRefresh", Boolean.class)){
            throw new RuntimeException();
        }

        return jwtTokenService.generateToken(claims.get("userId", String.class));
    }
}
