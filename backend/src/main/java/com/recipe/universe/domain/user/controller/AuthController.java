package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.user.controller.form.UserRegistForm;
import com.recipe.universe.domain.user.jwt.dto.JwtTokenDto;
import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.service.UserService;
import com.recipe.universe.domain.user.service.authentication.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
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

}
