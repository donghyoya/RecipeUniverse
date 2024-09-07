package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.user.controller.form.UserRegistForm;
import com.recipe.universe.domain.user.jwt.dto.JwtTokenDto;
import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private JwtTokenService jwtTokenService;

    @PostMapping("/regist")
    public String regist(@RequestBody UserRegistForm form){
        Long save = userService.save(form.getId(), form.getPassword());
        return "Login Success";
    }


}
