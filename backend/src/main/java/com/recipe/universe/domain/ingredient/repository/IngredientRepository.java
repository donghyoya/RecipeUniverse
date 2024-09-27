package com.recipe.universe.domain.ingredient.repository;

import com.recipe.universe.domain.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByIngName(String ingName);
    // like %ingName%
    Optional<List<Ingredient>> findByIngNameContaining(String ingName);
}
