package com.recipe.universe.domain.user.role.repository.cache;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.List;

/**
 * CacheObject
 */
@Getter
public class UserRoleCO {
    private List<String> role;

    public UserRoleCO(){}

    public UserRoleCO(List<String> role) {
        this.role = role;
    }
}
