package com.recipe.universe.domain.user.history.repository;

import com.recipe.universe.domain.user.history.entity.UserHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Page<UserHistory> findByUserIdOrderByRegDate(Long userId, Pageable pageable);
}
