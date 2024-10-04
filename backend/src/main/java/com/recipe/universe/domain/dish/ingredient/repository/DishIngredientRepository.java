package com.recipe.universe.domain.dish.ingredient.repository;

import com.recipe.universe.domain.dish.ingredient.entity.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {
}
