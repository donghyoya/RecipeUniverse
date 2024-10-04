package com.recipe.universe.domain.dish.ingredient.service;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.ingredient.dto.DishIngredientDto;
import com.recipe.universe.domain.dish.ingredient.entity.DishIngredient;
import com.recipe.universe.domain.dish.ingredient.repository.DishIngredientRepository;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
import com.recipe.universe.domain.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DishIngredientService {

    private final DishIngredientRepository dishIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;

    /* CREATE */
    @Transactional
    public Long createDishIngredient(String ingredientName, Double amount, String unit, Dish dish){
        DishIngredient ingredient = null;
        Optional<Ingredient> optionIng = ingredientRepository.findByIngName(ingredientName);
        Ingredient ing;
        if(optionIng.isEmpty()){
           ing = new Ingredient(ingredientName, "유저추가", unit!=null ? unit : "개");
           ingredientRepository.save(ing);
        }else {
            ing = optionIng.get();
        }

        if(unit == null){
            ingredient = new DishIngredient(amount, dish, ing);
        }else {
            ingredient = new DishIngredient(amount, unit, dish, ing);
        }
        Long id = dishIngredientRepository.save(ingredient).getDiId();
        return id;
    }

    /* READ */

    public List<DishIngredientDto> findByDishId(Long dishId) {
        return dishIngredientRepository.findByDishId(dishId).stream().map(DishIngredientDto::new).toList();
    }
}
