package com.recipe.universe.domain.user.oauth2.dto;

import com.recipe.universe.domain.user.user.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

public class CustomOidcUser extends DefaultOidcUser {
    private Long id;
    private String provider;
    private String username;

    public CustomOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken, UserDto user) {
        super(authorities, idToken);
        this.id = user.getId();
        this.username = user.getUsername();
        this.provider = user.getProvider();
    }

    public Long getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public String getUsername() {
        return username;
    }
}
