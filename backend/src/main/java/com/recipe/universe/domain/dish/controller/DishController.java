package com.recipe.universe.domain.dish.controller;

import com.recipe.universe.domain.dish.controller.form.CreateDishForm;
import com.recipe.universe.domain.dish.controller.form.UpdateDishForm;
import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.service.DishService;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id){
        dishService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}")
    public DishWithRecipeDto updateDish(@PathVariable("id") Long id, @RequestBody UpdateDishForm form){
        dishService.updateDish(id, form);
        return dishService.findDishWithRecipeById(id);
    }

}
