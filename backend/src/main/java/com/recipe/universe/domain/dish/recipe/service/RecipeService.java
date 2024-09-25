package com.recipe.universe.domain.dish.recipe.service;

import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.recipe.dto.RecipeDto;
import com.recipe.universe.domain.dish.recipe.entity.Recipe;
import com.recipe.universe.domain.dish.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    /* CREATE */

    @Transactional
    public Long createRecipe(Long num, String description, Dish dish){
        Recipe recipe = new Recipe(num, description, dish);
        Long id = recipeRepository.save(recipe).getId();
        return id;
    }

    /* READ */

    public List<RecipeDto> findRecipeByDishId(Long dishId){
        return recipeRepository.findByDishIdOrderByRecipeNum(dishId).stream().map(RecipeDto::new).toList();
    }

    /* UPDATE */

    @Transactional
    public void updateRecipe(Long id, Long num, String description){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipe.updateRecipe(num, description);
    }

    /* DELETE */

    @Transactional
    public void deleteRecipe(Long num, Long dishId){
        Recipe recipe = recipeRepository.findByRecipeNumAndDishId(num, dishId).orElseThrow();
        recipe.delete();
    }

    @Transactional
    public void deleteRecipe(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipe.delete();
    }
}
