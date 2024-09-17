package com.recipe.universe.domain.user.repository;

import com.recipe.universe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String username);

}
