package com.recipe.universe.domain.user.dto;

import com.recipe.universe.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String provider;

    public UserDto(Long id, String username, String email, String provider) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.provider = provider;
    }

    public static UserDto convert(User user){
        return convert(user.getId(), user);
    }

    public static UserDto convert(Long id, User user){
        return new UserDto(
                id,
                user.getUserId(),
                user.getEmail(),
                user.getProvider()
        );
    }

}
