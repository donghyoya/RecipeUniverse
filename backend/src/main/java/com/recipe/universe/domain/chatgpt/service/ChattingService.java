package com.recipe.universe.domain.chatgpt.service;

import com.recipe.universe.domain.chatgpt.repository.ChattingRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepostory chattingRepostory;
}
