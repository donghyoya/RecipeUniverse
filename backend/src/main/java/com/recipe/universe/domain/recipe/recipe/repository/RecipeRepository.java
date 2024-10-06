package com.recipe.universe.domain.recipe.recipe.repository;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByUserId(Long userId);

    @Query("select r from Recipe r join fetch r.user where r.id = :id")
    Optional<Recipe> checkAuthForRecipe(Long id);


    @Query("select r from Recipe r join fetch r.steps where r.id = :id")
    Optional<Recipe> findDishWithRecipeById(Long id);
}