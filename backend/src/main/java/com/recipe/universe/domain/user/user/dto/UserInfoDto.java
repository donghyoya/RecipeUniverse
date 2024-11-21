package com.recipe.universe.domain.user.user.dto;

import com.recipe.universe.domain.user.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfoDto {
    private Long userId;
    private String nickname;

    public UserInfoDto(Long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public UserInfoDto(User user){
        this.userId = user.getId();
        this.nickname = user.getNickname();
    }
}
