package com.recipe.universe.domain.recipe.controller;

import com.recipe.universe.domain.recipe.controller.form.dish.CreateDishForm;
import com.recipe.universe.domain.recipe.controller.form.dish.UpdateDishForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.service.RecipeService;
import com.recipe.universe.domain.recipe.ingredient.dto.DishIngredientDto;
import com.recipe.universe.domain.recipe.ingredient.service.DishIngredientService;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import com.recipe.universe.domain.recipe.step.service.RecipeStepService;
import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.rating.dto.UserDishRatingsDto;
import com.recipe.universe.domain.rating.service.UserDishRatingsService;
import com.recipe.universe.global.dto.BaseListResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/dish")
@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;
    private final UserDishRatingsService ratingsService;
    private final UserLikeService userLikeService;
    private final DishIngredientService dishIngredientService;

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public RecipeCompleteDto createDish(@RequestBody CreateDishForm form, Authentication authentication){
        Long dishId = recipeService.createRecipe(
                Long.parseLong(authentication.getName()),
                form.getDishName(),
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
    public BaseListResponse<RecipeDto> getDish(){
        return new BaseListResponse<>(recipeService.findAllRecipes());
    }

    @GetMapping("/{id}")
    public RecipeCompleteDto getDishByDishId(@PathVariable("id") Long id){
        return recipeService.findRecipeComplete(id);
    }

    @GetMapping("/{id}/steps")
    public BaseListResponse<RecipeStepDto> getStepsByDishId(@PathVariable("id") Long id){
        return new BaseListResponse<>(recipeStepService.findStepByDishId(id));
    }

    @GetMapping("/{id}/ingredient")
    public BaseListResponse<DishIngredientDto> getIngredientByDishId(@PathVariable("id") Long id){
        return new BaseListResponse<>(dishIngredientService.findByDishId(id));
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id){
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/update")
    public RecipeCompleteDto updateDish(@PathVariable("id") Long id, @RequestBody UpdateDishForm form){
        recipeService.updateRecipe(id, form);
        return recipeService.findRecipeComplete(id);
    }

    @GetMapping("/{id}/ratings")
    public BaseListResponse<UserDishRatingsDto> getRatings(@PathVariable("id") Long id){
        return new BaseListResponse<>(ratingsService.findByDishId(id));
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeDish(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.likeDish(userId, id);
        return ResponseEntity.ok("like success");
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/unlike")
    public ResponseEntity<String> unlikeDish(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.unlikeDish(userId, id);
        return ResponseEntity.ok("unlike success");
    }

}
