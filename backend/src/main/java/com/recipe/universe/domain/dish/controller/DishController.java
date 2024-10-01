package com.recipe.universe.domain.dish.controller;

import com.recipe.universe.domain.dish.controller.form.CreateDishForm;
import com.recipe.universe.domain.dish.controller.form.UpdateDishForm;
import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.service.DishService;
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
public class DishController {
    private final DishService dishService;
    private final UserDishRatingsService ratingsService;
    private final UserLikeService userLikeService;

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public DishWithRecipeDto createDish(@RequestBody CreateDishForm form, Authentication authentication){
        Long dishId = dishService.createDish(
                Long.parseLong(authentication.getName()),
                form.getDishName(),
                form.getDescription(),
                form.getPreparationTime(),
                form.getCookingTime(),
                form.getServingSize(),
                form.getRecipeLevel(),
                form.getRecipes()
        );
        return dishService.findDishWithRecipeById(dishId);
    }

    @GetMapping
    public BaseListResponse<DishDto> getDish(){
        return new BaseListResponse<>(dishService.findAllDish());
    }

    @GetMapping("/{id}")
    public DishWithRecipeDto getDishByDishId(@PathVariable("id") Long id){
        return dishService.findDishWithRecipeById(id);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id){
        dishService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/update")
    public DishWithRecipeDto updateDish(@PathVariable("id") Long id, @RequestBody UpdateDishForm form){
        dishService.updateDish(id, form);
        return dishService.findDishWithRecipeById(id);
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

}
