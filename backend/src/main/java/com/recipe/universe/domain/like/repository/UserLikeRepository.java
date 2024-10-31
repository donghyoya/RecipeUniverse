package com.recipe.universe.domain.like.repository;

import com.recipe.universe.domain.like.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    /* Dish */
    Boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
    Optional<UserLike> findByUserIdAndRecipeId(Long userId, Long recipeId);

    /* Rating */
    Boolean existsByUserIdAndReviewId(Long userId, Long reviewId);
    Optional<UserLike> findByUserIdAndReviewId(Long userId, Long reviewId);

    /* 유저 좋아요 찾기 */

    @Query("select like from UserLike like join fetch like.recipe where like.delFlag = false")
    Page<UserLike> findRecipeByUserId(Long userId, Pageable pageable);

    @Query("select like from UserLike like join fetch like.review where like.delFlag = false")
    Page<UserLike> findReviewByUserId(Long userId, Pageable pageable);

    @Query("select count(*) from UserLike like where like.recipeId = :recipeId and like.delFlag = false")
    int countRecipeLike(Long recipeId);

    @Query("select count(*) from UserLike like where like.reviewId = :reviewId and like.delFlag = false")
    int countReviewLike(Long reviewId);


}
