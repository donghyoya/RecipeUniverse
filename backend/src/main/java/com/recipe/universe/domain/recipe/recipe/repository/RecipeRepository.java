package com.recipe.universe.domain.recipe.recipe.repository;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByUserId(Long userId, Pageable pageable);

    @Query("select r from Recipe r join fetch r.user where r.id = :id")
    Optional<Recipe> checkAuthForRecipe(Long id);


    @Query("select r from Recipe r join fetch r.steps where r.id = :id")
    Optional<Recipe> findRecipeWithStepById(Long id);

    @Query("select r from Recipe r join fetch r.recipeHashTags where r.id in :ids")
    List<Recipe> findRecipeWithHashTagByIdIn(List<Long> ids);

    Page<Recipe> findByName(String name, Pageable pageable);
}
