package com.recipe.universe.domain.recipe.ingredient.repository;

import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    @Query("select ri from RecipeIngredient ri join fetch ri.ingredient where ri.recipeId = :recipeId")
    List<RecipeIngredient> findByRecipeId(@Param("recipeId") Long recipeId);
}
