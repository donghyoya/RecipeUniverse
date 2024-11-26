package com.recipe.universe.domain.chatgpt.repository;

import com.recipe.universe.domain.chatgpt.entity.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGptRepository extends JpaRepository<ChatInfo, Long> {
}
