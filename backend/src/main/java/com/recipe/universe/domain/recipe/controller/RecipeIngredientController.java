package com.recipe.universe.domain.recipe.controller;

import com.recipe.universe.domain.ingredient.dto.ReadIngredientDto;
import com.recipe.universe.domain.ingredient.service.IngredientService;
import com.recipe.universe.global.dto.BaseListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe/ing")
public class RecipeIngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{queryString}")
    public BaseListResponse<ReadIngredientDto> queryByName(
            @PathVariable("queryString") String queryString
    ){
        return new BaseListResponse<>(ingredientService.findByLikeIngName(queryString));
    }

}
