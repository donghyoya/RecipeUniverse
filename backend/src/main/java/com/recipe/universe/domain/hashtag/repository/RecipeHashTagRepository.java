package com.recipe.universe.domain.hashtag.repository;

import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecipeHashTagRepository extends JpaRepository<RecipeHashTag, Long> {
    @Query("select rt from RecipeHashTag rt join fetch rt.hashTag tag where tag.tagname= :tagname and rt.recipeId= :recipeId")
    Optional<RecipeHashTag> findByTagnameAndRecipeId(String tagname, Long recipeId);
}
