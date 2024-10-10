package com.recipe.universe.domain.review.repository;

import com.recipe.universe.domain.review.entity.UserReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    Page<UserReview> findByUserId(Long userId, Pageable pageable);

    Page<UserReview> findByRecipeId(Long recipeId, Pageable pageable);
}
