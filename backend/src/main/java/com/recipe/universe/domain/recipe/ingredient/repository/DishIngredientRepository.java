package com.recipe.universe.domain.recipe.ingredient.repository;

import com.recipe.universe.domain.recipe.ingredient.entity.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {
    @Query("select di from DishIngredient di join fetch di.ingredient where di.dishId = :dishId")
    List<DishIngredient> findByDishId(Long dishId);
}
