package com.recipe.universe.domain.recipe.recipe.repository;

import com.recipe.universe.domain.recipe.recipe.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByUserId(Long userId);

    @Query("select d from Dish d join fetch d.user where d.id = :id")
    Optional<Dish> checkAuthForDish(Long id);


    @Query("select d from Dish d join fetch d.steps where d.id = :id")
    Optional<Dish> findDishWithRecipeById(Long id);
}
