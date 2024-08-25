package com.recipe.universe.domain.images.repository;

import com.recipe.universe.domain.images.entity.ImageFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFilesRepository extends JpaRepository<ImageFiles, Long> {
}
