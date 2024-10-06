package com.recipe.universe.domain.dish.step.service;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.step.dto.RecipeDto;
import com.recipe.universe.domain.dish.step.entity.RecipeStep;
import com.recipe.universe.domain.dish.step.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    /* CREATE */

    @Transactional
    public Long createRecipe(Long num, String description, Dish dish){
        RecipeStep recipeStep = new RecipeStep(num, description, dish);
        Long id = recipeRepository.save(recipeStep).getId();
        return id;
    }

    /* READ */

    public List<RecipeDto> findRecipeByDishId(Long dishId){
        return recipeRepository.findByDishIdOrderByOrder(dishId).stream().map(RecipeDto::new).toList();
    }

    /* UPDATE */

    @Transactional
    public void updateRecipe(Long id, Long num, String description){
        RecipeStep recipeStep = recipeRepository.findById(id).orElseThrow();
        recipeStep.updateRecipe(num, description);
    }

    /* DELETE */

    @Transactional
    public void deleteRecipe(Long num, Long dishId){
        RecipeStep recipeStep = recipeRepository.findByOrderAndDishId(num, dishId).orElseThrow();
        recipeStep.delete();
    }

    @Transactional
    public void deleteRecipe(Long id){
        RecipeStep recipeStep = recipeRepository.findById(id).orElseThrow();
        recipeStep.delete();
    }
}
