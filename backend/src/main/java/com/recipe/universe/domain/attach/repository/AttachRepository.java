package com.recipe.universe.domain.attach.repository;

import com.recipe.universe.domain.attach.entity.AttachFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachRepository extends JpaRepository<AttachFiles, Long> {

    Optional<AttachFiles> findByFileType(String typeName);

    Optional<AttachFiles> findByOrgFileName(String orgFileName);
}
