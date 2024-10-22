package com.recipe.universe.domain.hashtag.repository;

import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeHashTagRepository extends JpaRepository<RecipeHashTag, Long> {
}
