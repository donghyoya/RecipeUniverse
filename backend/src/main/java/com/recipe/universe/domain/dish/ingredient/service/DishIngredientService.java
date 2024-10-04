package com.recipe.universe.domain.dish.ingredient.service;

import com.recipe.universe.domain.dish.ingredient.repository.DishIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishIngredientService {

    private final DishIngredientRepository dishIngredientRepository;

    /* 일단 형식으로 생성해둠 */
}
