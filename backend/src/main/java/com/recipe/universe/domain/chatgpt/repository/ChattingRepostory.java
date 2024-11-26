package com.recipe.universe.domain.chatgpt.repository;

import com.recipe.universe.domain.chatgpt.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepostory extends JpaRepository<Chatting, Long> {
}
