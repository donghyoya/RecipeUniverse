package com.recipe.universe.domain.like.repository;

import com.recipe.universe.domain.like.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
}
