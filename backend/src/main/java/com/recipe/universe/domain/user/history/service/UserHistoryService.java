package com.recipe.universe.domain.user.history.service;

import com.recipe.universe.domain.user.history.dto.UserHistoryDto;
import com.recipe.universe.domain.user.history.entity.UserHistory;
import com.recipe.universe.domain.user.history.repository.UserHistoryRepository;
import com.recipe.universe.domain.user.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserHistoryService {
    private final UserHistoryRepository historyRepository;

    @Transactional
    public Long createUserHistory(User user){
        UserHistory userHistory = new UserHistory(user);
        Long id = historyRepository.save(userHistory).getId();
        return id;
    }

    public Page<UserHistoryDto> findUserHistoryByUserId(Long userId, int page, int size){
        return historyRepository.findByUserIdOrderByRegDate(userId, PageRequest.of(page, size)).map(UserHistoryDto::new);
    }
}
