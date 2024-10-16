package com.recipe.universe.domain.recipe.ingredient.service;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.ingredient.dto.RecipeIngredientDto;
import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import com.recipe.universe.domain.recipe.ingredient.repository.RecipeIngredientRepository;
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
public class RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;

    /* CREATE */
    @Transactional
    public Long createRecipeIngredient(Double amount, String unit, String description, Boolean optional, String ingredientName, Recipe recipe){
        Ingredient ing = findIngByName(ingredientName, unit);
        return recipeIngredientRepository.save(
                RecipeIngredient.builder()
                        .recipe(recipe)
                        .amount(amount)
                        .unit(unit)
                        .ingredient(ing)
                        .optional(optional)
                        .description(description)
                    .build()
        ).getId();
    }

    private Ingredient findIngByName(String name, String unit){
        Optional<Ingredient> optionIng = ingredientRepository.findByIngName(name);
        Ingredient ing;
        if(optionIng.isEmpty()){
            ing = new Ingredient(name, "유저추가재료");
            ingredientRepository.save(ing);
        }else {
            ing = optionIng.get();
        }
        return ing;
    }

    /* READ */

    public List<RecipeIngredientDto> findByRecipeId(Long recipeId) {
        return recipeIngredientRepository.findByRecipeId(recipeId).stream().map(RecipeIngredientDto::new).toList();
    }

    /* Delete */
    @Transactional
    public void deleteById(Long id) {
        RecipeIngredient ingredient = recipeIngredientRepository.findById(id).orElseThrow();
        ingredient.delete();
    }

    @Transactional
    public void update(Long id, Double amount, String unit, String description, Boolean optional) {
        RecipeIngredient ingredient = recipeIngredientRepository.findById(id).orElseThrow();
        ingredient.update(amount, unit, description, optional);
    }
}
