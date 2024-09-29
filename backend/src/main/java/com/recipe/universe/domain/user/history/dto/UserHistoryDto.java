package com.recipe.universe.domain.user.history.dto;

import com.recipe.universe.domain.user.history.entity.UserHistory;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class UserHistoryDto {
    private Long userId;
    private LocalDateTime date;

    public UserHistoryDto(UserHistory userHistory) {
        this.userId = userHistory.getUserId();
        this.date = userHistory.getRegDate();
    }
}
