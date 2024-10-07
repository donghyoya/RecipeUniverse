package com.recipe.universe.domain.recipe.controller;

import com.recipe.universe.domain.recipe.controller.form.recipe.CreateRecipeForm;
import com.recipe.universe.domain.recipe.controller.form.recipe.UpdateRecipeForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;
    private final UserReviewService reviewService;
    private final UserLikeService userLikeService;
    private final RecipeIngredientService recipeIngredientService;

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public RecipeCompleteDto createRecipe(@RequestBody CreateRecipeForm form, Authentication authentication){
        Long dishId = recipeService.createRecipe(
                Long.parseLong(authentication.getName()),
                form.getName(),
                form.getDescription(),
                form.getPreparationTime(),
                form.getCookingTime(),
                form.getServingSize(),
                form.getRecipeLevel(),
                form.getSteps(),
                form.getIngredients()
        );
        return recipeService.findRecipeComplete(dishId);
    }

    @GetMapping
    public BasePageResponse<RecipeDto> getRecipe(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ){
        return BasePageResponse.of(recipeService.findAllRecipes(page, size));
    }

    @GetMapping("/{id}")
    public RecipeCompleteDto getById(@PathVariable("id") Long id){
        return recipeService.findRecipeComplete(id);
    }

    @GetMapping("/{id}/steps")
    public BaseListResponse<RecipeStepDto> getStepsById(@PathVariable("id") Long id){
        return new BaseListResponse<>(recipeStepService.findStepByDishId(id));
    }

    @GetMapping("/{id}/ingredient")
    public BaseListResponse<RecipeIngredientDto> getIngredientById(@PathVariable("id") Long id){
        return new BaseListResponse<>(recipeIngredientService.findByRecipeId(id));
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Long id){
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/update")
    public RecipeCompleteDto updateRecipe(@PathVariable("id") Long id, @RequestBody UpdateRecipeForm form){
        recipeService.updateRecipe(id, form);
        return recipeService.findRecipeComplete(id);
    }

    @GetMapping("/{id}/review")
    public BasePageResponse<UserReviewDto> getRatings(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ){
        return BasePageResponse.of(reviewService.findByRecipeId(id, page, size));
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeRecipe(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.likeDish(userId, id);
        return ResponseEntity.ok("like success");
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/unlike")
    public ResponseEntity<String> unlikeRecipe(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.unlikeDish(userId, id);
        return ResponseEntity.ok("unlike success");
    }

}
