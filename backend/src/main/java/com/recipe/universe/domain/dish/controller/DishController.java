package com.recipe.universe.domain.dish.controller;

import com.recipe.universe.domain.dish.controller.form.CreateDishForm;
import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.service.DishService;
import com.recipe.universe.domain.user.oauth2.dto.CustomOidcUser;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.global.dto.BaseListResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/dish")
@RestController
public class DishController {
    private final DishService dishService;

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

}
