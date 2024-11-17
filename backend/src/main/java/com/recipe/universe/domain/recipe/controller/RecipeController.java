package com.recipe.universe.domain.recipe.controller;

import com.recipe.universe.domain.hashtag.service.HashTagSearchService;
import com.recipe.universe.domain.like.dto.UserLikeDto;
import com.recipe.universe.domain.recipe.controller.form.RecipeSearchType;
import com.recipe.universe.domain.recipe.controller.form.RecipeSortOption;
import com.recipe.universe.domain.recipe.controller.form.recipe.CreateRecipeForm;
import com.recipe.universe.domain.recipe.controller.form.recipe.UpdateRecipeForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeWithHashTagDto;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import com.recipe.universe.domain.recipe.recipe.service.RecipeQueryService;
import com.recipe.universe.domain.recipe.recipe.service.RecipeService;
import com.recipe.universe.domain.recipe.ingredient.dto.RecipeIngredientDto;
import com.recipe.universe.domain.recipe.ingredient.service.RecipeIngredientService;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import com.recipe.universe.domain.recipe.step.service.RecipeStepService;
import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.service.UserReviewService;
import com.recipe.universe.global.dto.BaseListResponse;
import com.recipe.universe.global.dto.BasePageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "레시피")
@RequiredArgsConstructor
@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeQueryService recipeQueryService;
    private final RecipeStepService recipeStepService;
    private final UserReviewService reviewService;
    private final UserLikeService userLikeService;
    private final RecipeIngredientService recipeIngredientService;
    private final HashTagSearchService hashTagSearchService;

    @Operation(summary = "레시피 작성", description = "레시피를 작성한다")
    @SecurityRequirement(name = "JWT")
    @PostMapping
    public RecipeCompleteDto createRecipe(@RequestBody CreateRecipeForm form, Authentication authentication){
        Long dishId = recipeService.createRecipe(
                Long.parseLong(authentication.getName()),
                form.getName(),
                form.getDescription(),
                form.getPreparationTime(),
                form.getServingSize(),
                form.getDifficulty(),
                form.getSteps(),
                form.getIngredients(),
                form.getTagnames()
        );
        return recipeService.findRecipeComplete(dishId);
    }

    @Operation(summary = "레시피 검색", description = "검색조건이 있다면 검색조건에 맞는 레시피를, 그렇지 않다면 최신작성된 레시피 순으로 불러온다 ")
    @GetMapping
    public BasePageResponse<RecipeDto> getRecipe(
            @RequestParam(value = "recipeName", required = false) String recipeName,
            @RequestParam(value = "difficulty", required = false) RecipeDifficulty difficulty,
            @RequestParam(value = "cookingTime", required = false) Integer cookingTime,
            @RequestParam(value = "servingSize", required = false) Integer servingSize,
            @RequestParam(value = "sortOption", defaultValue = "Latest") RecipeSortOption sortOption,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ){
        Page<RecipeDto> recipeDtos = recipeQueryService.searchRecipe(
                recipeName,
                difficulty,
                cookingTime,
                servingSize,
                sortOption,
                page, size
        );
        return BasePageResponse.of(recipeDtos);
    }

    @Operation(summary = "개별 레시피 열람", description = "id를 사용하여 개별 레시피 접근")
    @GetMapping("/{id}")
    public RecipeCompleteDto getById(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id){
        return recipeService.findRecipeComplete(id);
    }

    @Operation(summary = "조리법 열람", description = "개별 조리법 열람")
    @GetMapping("/{id}/steps")
    public BaseListResponse<RecipeStepDto> getStepsById(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id){
        return new BaseListResponse<>(recipeStepService.findStepByDishId(id));
    }

    @Operation(summary = "재료 열람", description = "요리에 사용되는 재료 열람 ")
    @GetMapping("/{id}/ingredient")
    public BaseListResponse<RecipeIngredientDto> getIngredientById(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id){
        return new BaseListResponse<>(recipeIngredientService.findByRecipeId(id));
    }

    @Operation(summary = "레시피 삭제", description = "레시피 삭제")
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRecipe(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id){
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "레시피 수정", description = "레시피 수정 ")
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/update")
    public RecipeCompleteDto updateRecipe(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id,
            @RequestBody UpdateRecipeForm form){
        recipeService.updateRecipe(id, form);
        return recipeService.findRecipeComplete(id);
    }

    @Operation(summary = "레시피 리뷰 요청", description = "레시피 리뷰 요청")
    @GetMapping("/{id}/review")
    public BasePageResponse<UserReviewDto> getRatings(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ){
        return BasePageResponse.of(reviewService.findByRecipeId(id, page, size));
    }

    /*
    * Toggle방식으로 작동. 좋아요가 되었는지 안되었는지는 이미 프런트엔드에서 알고 있으며, UserLikeDto를 통해 좋아요가 되었는지
    * */
    @Operation(summary = "좋아요 또는 좋아요 해제", description = "토글방식(좋아요가 되어있으면 알아서 좋아요해제 됨)으로 작동함. get으로 먼저 좋아요를 획득할 것")
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/like")
    public UserLikeDto toggleRecipe(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id,
            Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return userLikeService.toggleRecipeUser(userId, id);
    }

    @Operation(summary = "좋아요 여부 확인", description = "좋아요 여부 및 해당 레시피의 좋아요 개수 획득")
    @GetMapping("/{id}/like")
    public UserLikeDto getRecipeLike(
            @Parameter(description = "레시피 id", example = "158")
            @PathVariable("id") Long id,
            Authentication authentication){
        Long userId = 0l;
        if(authentication != null && authentication.isAuthenticated()){
            userId = Long.parseLong(authentication.getName());
        }
        return userLikeService.getLikeRecipe(userId, id);
    }


}
