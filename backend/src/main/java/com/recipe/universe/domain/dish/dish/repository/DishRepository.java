package com.recipe.universe.domain.dish.dish.repository;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByUserId(Long userId);
}
