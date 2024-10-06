package com.recipe.universe.domain.dish.step.repository;

import com.recipe.universe.domain.dish.step.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<RecipeStep, Long> {
    Optional<RecipeStep> findByOrderAndDishId(Long order, Long dishId);

    List<RecipeStep> findByDishIdOrderByOrder(Long dishId);
}
