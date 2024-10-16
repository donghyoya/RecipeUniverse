package com.recipe.universe.domain.ingredient.repository;

import com.recipe.universe.domain.ingredient.entity.IngUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngUnitRepository extends JpaRepository<IngUnit, Long> {
}
