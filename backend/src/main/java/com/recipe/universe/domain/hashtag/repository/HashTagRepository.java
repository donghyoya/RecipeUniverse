package com.recipe.universe.domain.hashtag.repository;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    Optional<HashTag> findByTagname(String tagname);
}
