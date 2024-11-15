package com.recipe.universe.domain.hashtag.repository;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, String> {
    Optional<HashTag> findByTagname(String tagname);

    @Query("select t from HashTag t where t.tagname = :tagname")
    Optional<HashTag> findRecipeHashTagByTagname(@Param("tagname") String tagname);
}
