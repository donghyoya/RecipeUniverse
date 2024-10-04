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
    public Long createDishIngredient(Double amount, String unit, String description, Boolean optional,String ingredientName, Dish dish){
        Ingredient ing = findIngByName(ingredientName, unit);
        return dishIngredientRepository.save(
                DishIngredient.builder()
                        .dish(dish)
                        .dAmount(amount)
                        .unit(unit)
                        .ingredient(ing)
                        .optional(optional)
                        .description(description)
                    .build()
        ).getDiId();
    }

    private Ingredient findIngByName(String name, String unit){
        Optional<Ingredient> optionIng = ingredientRepository.findByIngName(name);
        Ingredient ing;
        if(optionIng.isEmpty()){
            ing = new Ingredient(name, "유저추가", unit!=null ? unit : "개");
            ingredientRepository.save(ing);
        }else {
            ing = optionIng.get();
        }
        return ing;
    }

    /* READ */

    public List<DishIngredientDto> findByDishId(Long dishId) {
        return dishIngredientRepository.findByDishId(dishId).stream().map(DishIngredientDto::new).toList();
    }
}
