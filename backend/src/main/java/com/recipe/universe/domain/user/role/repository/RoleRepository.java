package com.recipe.universe.domain.user.role.repository;

import com.recipe.universe.domain.user.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
