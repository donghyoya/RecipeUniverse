package com.recipe.universe.domain.hashtag.repository;

import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecipeHashTagRepository extends JpaRepository<RecipeHashTag, Long> {
    @Query("select rt from RecipeHashTag rt join fetch rt.hashTag tag where tag.tagname= :tagname and rt.recipeId= :recipeId")
    Optional<RecipeHashTag> findByTagnameAndRecipeId(@Param("tagname") String tagname, @Param("recipeId") Long recipeId);

    Page<RecipeHashTag> findByTagname(@Param("tagname") String tagname, Pageable pageable);
}
