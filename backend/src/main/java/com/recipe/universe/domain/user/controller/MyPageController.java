package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeWithHashTagDto;
import com.recipe.universe.domain.recipe.recipe.service.RecipeService;
import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.dto.UserReviewWithLikeDto;
import com.recipe.universe.domain.review.service.UserReviewService;
import com.recipe.universe.domain.user.history.dto.UserHistoryDto;
import com.recipe.universe.domain.user.user.service.MyPageService;
import com.recipe.universe.domain.user.user.service.UserService;
import com.recipe.universe.global.dto.BaseListResponse;
import com.recipe.universe.global.dto.BasePageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "마이페이지 API", description = "내가 작성한 리뷰 및 레시피 / 좋아요한 리뷰, 레시피 보기")
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("/mypage")
public class MyPageController {
    private final UserLikeService userLikeService;
    private final UserService userService;
    private final MyPageService myPageService;

    @Operation(summary = "내가 작성한 리뷰")
    @GetMapping("/review")
    public BasePageResponse<UserReviewWithLikeDto> getMyRatings(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(myPageService.findMyPageReview(userId, page,size));
    }

    @Operation(summary = "내가 작성한 레시피")
    @GetMapping("/recipes")
    public BasePageResponse<RecipeSearchDto> getMyRecipes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(myPageService.findMyPageRecipes(userId, page, size));
    }

    @Operation(summary = "로그인 기록 확인")
    @GetMapping("/history")
    public BasePageResponse<UserHistoryDto> getMyLoginHistories(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(userService.findUserHistoryById(userId, page, size));
    }

    @Operation(summary = "내가 좋아요한 레시피")
    @GetMapping("/like/recipes")
    public BasePageResponse<RecipeSearchDto> getUserLikeRecipes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(myPageService.findRecipeByUserLike(userId, page, size));
    }

    @Operation(summary = "내가 좋아요한 리뷰")
    @GetMapping("/like/review")
    public BasePageResponse<UserReviewDto> getUserLikeRating(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return BasePageResponse.of(userLikeService.findUserLikeRating(userId, page, size));
    }

}
