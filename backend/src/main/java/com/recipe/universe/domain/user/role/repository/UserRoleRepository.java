package com.recipe.universe.domain.user.role.repository;

import com.recipe.universe.domain.user.role.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select ur from UserRole ur join fetch ur.role where ur.userId = :userId")
    List<UserRole> findByUserId(@Param("userId") Long userId);

    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
}
