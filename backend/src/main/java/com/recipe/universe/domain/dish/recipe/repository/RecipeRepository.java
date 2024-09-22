package com.recipe.universe.domain.dish.recipe.repository;

import com.recipe.universe.domain.dish.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeNumAndDishId(Long recipeNum, Long dishId);

    List<Recipe> findByDishIdOrderByRecipeNum(Long dishId);
}
