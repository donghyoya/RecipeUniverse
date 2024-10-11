package com.recipe.universe.domain.user.role.repository.cache;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CacheObject
 */
@Getter
public class UserRoleCO {
    private List<String> roles = new ArrayList<>();

    public UserRoleCO(){}

    public UserRoleCO(List<String> roles) {
        this.roles = roles;
    }
}
