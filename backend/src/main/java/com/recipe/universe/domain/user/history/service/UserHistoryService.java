package com.recipe.universe.domain.user.history.service;

import com.recipe.universe.domain.user.history.dto.UserHistoryDto;
import com.recipe.universe.domain.user.history.entity.UserHistory;
import com.recipe.universe.domain.user.history.repository.UserHistoryRepository;
import com.recipe.universe.domain.user.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserHistoryService {
    private UserHistoryRepository historyRepository;

    @Transactional
    public Long createUserHistory(User user){
        UserHistory userHistory = new UserHistory(user);
        Long id = historyRepository.save(userHistory).getId();
        return id;
    }

    public List<UserHistoryDto> findUserHistoryByUserId(Long userId){
        return historyRepository.findByUserIdAndOrderByRegDate(userId).stream().map(UserHistoryDto::new).toList();
    }
}
