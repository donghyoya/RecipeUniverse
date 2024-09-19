package com.recipe.universe.domain.user.role.repository;

import com.recipe.universe.domain.user.role.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select ur from UserRole ur join fetch ur.role")
    List<UserRole> findByUserId(Long userId);
}
