package com.recipe.universe.domain.user.user.dto;

import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.global.dto.BaseListResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class UserAndRoleDto {
    private Long id;
    private String username;
    private String email;
    private String provider;
    private BaseListResponse<String> roles;

    public UserAndRoleDto(User user, List<String> roles) {
        this.id = user.getId();
        this.username = user.getUserId();
        this.email = user.getEmail();
        this.provider = user.getProvider();
        this.roles = new BaseListResponse<>(roles);
    }
}
