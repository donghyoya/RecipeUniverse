package com.recipe.universe.domain.nutrition.repository;

import com.recipe.universe.domain.nutrition.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
