package com.recipe.universe.domain.hashtag.repository;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
}
