package com.recipe.universe.domain.dish.controller;

import com.recipe.universe.domain.dish.controller.form.CreateDishForm;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.service.DishService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/dish")
@RestController
public class DishController {
    private final DishService dishService;

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public DishWithRecipeDto createDish(@RequestBody CreateDishForm form){
        Long dishId = dishService.createDish(
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

}
