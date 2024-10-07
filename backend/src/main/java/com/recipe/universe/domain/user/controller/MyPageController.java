package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.service.RecipeService;
import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.service.UserReviewService;
import com.recipe.universe.domain.user.history.dto.UserHistoryDto;
import com.recipe.universe.domain.user.user.service.UserService;
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
    private final UserReviewService reviewService;
    private final UserLikeService userLikeService;
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/review")
    public BaseListResponse<UserReviewDto> getMyRatings(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(reviewService.findByUserId(userId));
    }

    @GetMapping("/recipes")
    public BaseListResponse<RecipeDto> getMyRecipes(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(recipeService.findByUserId(userId));
    }

    @GetMapping("/history")
    public BaseListResponse<UserHistoryDto> getMyLoginHistories(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(userService.findUserHistoryById(userId));
    }

    @GetMapping("/like/recipes")
    public BaseListResponse<RecipeDto> getUserLikeRecipes(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(userLikeService.findUserLikeDish(userId));
    }

    @GetMapping("/like/rating")
    public BaseListResponse<UserReviewDto> getUserLikeRating(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return new BaseListResponse<>(userLikeService.findUserLikeRating(userId));
    }

}
