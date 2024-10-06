package com.recipe.universe.domain.recipe.step.service;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import com.recipe.universe.domain.recipe.step.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecipeStepService {
    private final RecipeStepRepository recipeStepRepository;

    /* CREATE */

    @Transactional
    public Long createStep(Long num, String description, Recipe recipe){
        RecipeStep recipeStep = new RecipeStep(num, description, recipe);
        Long id = recipeStepRepository.save(recipeStep).getId();
        return id;
    }

    /* READ */

    public List<RecipeStepDto> findStepByDishId(Long dishId){
        return recipeStepRepository.findByRecipeIdOrderByOrder(dishId).stream().map(RecipeStepDto::new).toList();
    }

    /* UPDATE */

    @Transactional
    public void updateStep(Long id, Long num, String description){
        RecipeStep recipeStep = recipeStepRepository.findById(id).orElseThrow();
        recipeStep.updateRecipe(num, description);
    }

    /* DELETE */

    @Transactional
    public void deleteStep(Long num, Long dishId){
        RecipeStep recipeStep = recipeStepRepository.findByOrderAndRecipeId(num, dishId).orElseThrow();
        recipeStep.delete();
    }

    @Transactional
    public void deleteStep(Long id){
        RecipeStep recipeStep = recipeStepRepository.findById(id).orElseThrow();
        recipeStep.delete();
    }
}
