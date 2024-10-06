package com.recipe.universe.domain.recipe.step.service;

import com.recipe.universe.domain.recipe.recipe.entity.Dish;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import com.recipe.universe.domain.recipe.step.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecipeStepService {
    private final RecipeRepository recipeRepository;

    /* CREATE */

    @Transactional
    public Long createStep(Long num, String description, Dish dish){
        RecipeStep recipeStep = new RecipeStep(num, description, dish);
        Long id = recipeRepository.save(recipeStep).getId();
        return id;
    }

    /* READ */

    public List<RecipeStepDto> findStepByDishId(Long dishId){
        return recipeRepository.findByDishIdOrderByOrder(dishId).stream().map(RecipeStepDto::new).toList();
    }

    /* UPDATE */

    @Transactional
    public void updateStep(Long id, Long num, String description){
        RecipeStep recipeStep = recipeRepository.findById(id).orElseThrow();
        recipeStep.updateRecipe(num, description);
    }

    /* DELETE */

    @Transactional
    public void deleteStep(Long num, Long dishId){
        RecipeStep recipeStep = recipeRepository.findByOrderAndDishId(num, dishId).orElseThrow();
        recipeStep.delete();
    }

    @Transactional
    public void deleteStep(Long id){
        RecipeStep recipeStep = recipeRepository.findById(id).orElseThrow();
        recipeStep.delete();
    }
}
