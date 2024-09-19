package com.recipe.universe.domain.user.role.repository;

import com.recipe.universe.domain.user.role.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
