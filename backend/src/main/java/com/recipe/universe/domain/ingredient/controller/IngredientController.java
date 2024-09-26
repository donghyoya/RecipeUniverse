package com.recipe.universe.domain.ingredient.controller;

import com.recipe.universe.domain.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;
}
