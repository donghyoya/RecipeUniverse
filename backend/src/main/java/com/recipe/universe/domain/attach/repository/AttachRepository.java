package com.recipe.universe.domain.attach.repository;

import com.recipe.universe.domain.attach.entity.AttachFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachRepository extends JpaRepository<AttachFiles, Long> {

}
