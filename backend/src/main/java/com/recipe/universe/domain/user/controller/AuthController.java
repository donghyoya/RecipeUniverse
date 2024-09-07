package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.user.controller.form.UserRegistForm;
import com.recipe.universe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;

    @PostMapping("/regist")
    public String regist(@RequestBody UserRegistForm form){
        Long save = userService.save(form.getId(), form.getPassword());
        return "Login Success";
    }
}
