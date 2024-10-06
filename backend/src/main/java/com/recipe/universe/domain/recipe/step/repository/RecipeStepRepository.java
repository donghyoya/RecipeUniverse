package com.recipe.universe.domain.recipe.step.repository;

import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
    Optional<RecipeStep> findByOrderAndDishId(Long order, Long dishId);

    List<RecipeStep> findByDishIdOrderByOrder(Long dishId);
}
