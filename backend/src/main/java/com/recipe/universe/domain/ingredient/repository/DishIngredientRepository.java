package com.recipe.universe.domain.ingredient.repository;

import com.recipe.universe.domain.ingredient.entity.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {
}
