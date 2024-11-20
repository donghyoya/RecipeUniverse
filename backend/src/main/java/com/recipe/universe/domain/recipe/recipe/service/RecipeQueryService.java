package com.recipe.universe.domain.recipe.recipe.service;

import com.recipe.universe.domain.recipe.controller.form.RecipeSearchType;
import com.recipe.universe.domain.recipe.controller.form.RecipeSortOption;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeQueryRepository;
import com.recipe.universe.domain.review.dto.UserReviewWithLikeDto;
import com.recipe.universe.domain.review.repository.UserReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RecipeQueryService {
    private final RecipeQueryRepository recipeQueryRepository;
    private final UserReviewQueryRepository userReviewQueryRepository;

    public Page<RecipeSearchDto> searchRecipe(
            String recipeName,
            RecipeDifficulty difficulty,
            Integer cookingTime,
            Integer servingSize,
            RecipeSortOption option,
            int page, int size
    ){
        return recipeQueryRepository.searchRecipe(
                recipeName,
                difficulty,
                cookingTime,
                servingSize,
                option,
                PageRequest.of(page,size)
        );
    }

    public Page<UserReviewWithLikeDto> findReviewByRecipeId(Long recipeId, int page, int size){
        return userReviewQueryRepository.findReviewByRecipeId(recipeId, PageRequest.of(page,size));
    }
}
