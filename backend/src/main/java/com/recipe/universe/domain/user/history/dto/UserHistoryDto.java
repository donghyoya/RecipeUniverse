package com.recipe.universe.domain.user.history.dto;

import com.recipe.universe.domain.user.history.entity.UserHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class UserHistoryDto {
    @Schema(name = "유저 고유번호")
    private Long userId;
    @Schema(name = "유저가 로그인한(소셜로그인만 기록함) 시간")
    private LocalDateTime date;

    public UserHistoryDto(UserHistory userHistory) {
        this.userId = userHistory.getUserId();
        this.date = userHistory.getRegDate();
    }
}
