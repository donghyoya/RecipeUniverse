package com.recipe.universe.domain.like.repository;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.like.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    /* Dish */
    Boolean existsByUserIdAndDishId(Long userId, Long dishId);
    UserLike findByUserIdAndDishId(Long userId, Long ratingId);

    /* Rating */
    Boolean existsByUserIdAndRatingId(Long userId, Long ratingId);
    UserLike findByUserIdAndRatingId(Long userId, Long ratingId);
}
