package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.service.DishService;
import com.recipe.universe.domain.rating.dto.UserDishRatingsDto;
import com.recipe.universe.domain.rating.service.UserDishRatingsService;
import com.recipe.universe.global.dto.BaseListResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("/mypage")
public class MyPageController {
    private final UserDishRatingsService ratingsService;
    private final DishService dishService;
    @GetMapping("/ratings")
    public BaseListResponse<UserDishRatingsDto> getMyRatings(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(ratingsService.findByUserId(userId));
    }

    @GetMapping("/dishes")
    public BaseListResponse<DishDto> getMyDishes(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(dishService.findByUserId(userId));
    }
}
