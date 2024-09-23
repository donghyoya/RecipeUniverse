package com.recipe.universe.domain.user.user.repository;

import com.recipe.universe.domain.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String username);
    Optional<User> findByUserIdAndProvider(String username, String provider);
}
