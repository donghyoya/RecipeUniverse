package com.recipe.universe.domain.recipe.controller;

import com.recipe.universe.domain.hashtag.service.HashTagSearchService;
import com.recipe.universe.domain.like.dto.UserLikeDto;
import com.recipe.universe.domain.recipe.controller.form.RecipeSearchType;
import com.recipe.universe.domain.recipe.controller.form.recipe.CreateRecipeForm;
import com.recipe.universe.domain.recipe.controller.form.recipe.UpdateRecipeForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeWithHashTagDto;
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
    private final HashTagSearchService hashTagSearchService;

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

    @GetMapping
    public BasePageResponse<RecipeWithHashTagDto> getRecipe(
            @RequestParam(value = "type", defaultValue = "None")RecipeSearchType type,
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ){
        if(type.equals(RecipeSearchType.None)){
            return BasePageResponse.of(recipeService.findAllRecipes(page, size));
        }else if(type.equals(RecipeSearchType.Tagname)){
           return BasePageResponse.of(hashTagSearchService.findByTagname(query, page, size));
        }else {
            return BasePageResponse.of(recipeService.findByName(query, page, size));
        }
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

    /*
    * Toggle방식으로 작동. 좋아요가 되었는지 안되었는지는 이미 프런트엔드에서 알고 있으며, UserLikeDto를 통해 좋아요가 되었는지
    * */
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/like")
    public UserLikeDto likeRecipe(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        return userLikeService.toggleRecipeUser(userId, id);
    }

}
