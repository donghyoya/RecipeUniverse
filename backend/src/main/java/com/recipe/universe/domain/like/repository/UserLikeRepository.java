package com.recipe.universe.domain.like.repository;

import com.recipe.universe.domain.like.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    /* Dish */
    Boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
    UserLike findByUserIdAndRecipeId(Long userId, Long recipeId);

    /* Rating */
    Boolean existsByUserIdAndRatingId(Long userId, Long ratingId);
    UserLike findByUserIdAndRatingId(Long userId, Long ratingId);

    /* 유저 좋아요 찾기 */

    @Query("select like from UserLike like join fetch like.recipe where like.delFlag = false")
    List<UserLike> findDishByUserId(Long userId);

    @Query("select like from UserLike like join fetch like.rating where like.delFlag = false")
    List<UserLike> findRatingByUserId(Long userId);

}
