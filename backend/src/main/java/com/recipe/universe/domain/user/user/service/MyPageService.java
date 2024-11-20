package com.recipe.universe.domain.user.user.service;

import com.recipe.universe.domain.like.repository.UserLikeQueryRepository;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeQueryRepository;
import com.recipe.universe.domain.review.dto.UserReviewWithLikeDto;
import com.recipe.universe.domain.review.repository.UserReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageService {
    private final RecipeQueryRepository recipeQueryRepository;
    private final UserReviewQueryRepository userReviewQueryRepository;
    private final UserLikeQueryRepository userLikeQueryRepository;

    public Page<RecipeSearchDto> findMyPageRecipes(Long userId, int page, int size){
        return recipeQueryRepository.findByUserId(userId, PageRequest.of(page,size));
    }

    public Page<UserReviewWithLikeDto> findMyPageReview(Long userId, int page, int size){
        return userReviewQueryRepository.findReviewByUserId(userId, PageRequest.of(page, size));
    }

    public Page<RecipeSearchDto> findRecipeByUserLike(Long userId, int page, int size){
        return userLikeQueryRepository.userLikeRecipe(userId, PageRequest.of(page,size));
    }

    public Page<UserReviewWithLikeDto> findReviewByUserLike(Long userId, int page, int size){
        return userLikeQueryRepository.userLikeReview(userId, PageRequest.of(page,size));
    }

}
