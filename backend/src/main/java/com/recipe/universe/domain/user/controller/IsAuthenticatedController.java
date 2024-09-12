package com.recipe.universe.domain.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 그냥 인증되었는지 테스트하기 위한 (개발과정에서나 쓸 것)
 */
@RestController
public class IsAuthenticatedController {
    @GetMapping("/isAuthenticated")
    public String isAuthenticated(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException();
        }
        return "OK";
    }
}
