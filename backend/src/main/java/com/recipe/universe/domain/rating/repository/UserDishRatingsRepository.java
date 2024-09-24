package com.recipe.universe.domain.rating.repository;

import com.recipe.universe.domain.rating.entity.UserDishRatings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDishRatingsRepository extends JpaRepository<UserDishRatings, Long> {
    List<UserDishRatings> findByUserId(Long userId);

    List<UserDishRatings> findByDishId(Long dishId);
}
