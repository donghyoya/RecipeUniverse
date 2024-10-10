package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.service.RecipeService;
import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.service.UserReviewService;
import com.recipe.universe.domain.user.history.dto.UserHistoryDto;
import com.recipe.universe.domain.user.user.service.UserService;
import com.recipe.universe.global.dto.BaseListResponse;
import com.recipe.universe.global.dto.BasePageResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("/mypage")
public class MyPageController {
    private final UserReviewService reviewService;
    private final UserLikeService userLikeService;
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/review")
    public BasePageResponse<UserReviewDto> getMyRatings(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(reviewService.findByUserId(userId, page, size));
    }

    @GetMapping("/recipes")
    public BasePageResponse<RecipeDto> getMyRecipes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(recipeService.findByUserId(userId, page, size));
    }

    @GetMapping("/history")
    public BasePageResponse<UserHistoryDto> getMyLoginHistories(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(userService.findUserHistoryById(userId, page, size));
    }

    @GetMapping("/like/recipes")
    public BasePageResponse<RecipeDto> getUserLikeRecipes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(userLikeService.findUserLikeDish(userId, page, size));
    }

    @GetMapping("/like/rating")
    public BasePageResponse<UserReviewDto> getUserLikeRating(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(userLikeService.findUserLikeRating(userId, page, size));
    }

}
