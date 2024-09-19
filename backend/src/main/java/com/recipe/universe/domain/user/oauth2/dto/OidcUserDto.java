package com.recipe.universe.domain.user.oauth2.dto;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class OidcUserDto {
    private String provider;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public OidcUserDto(String provider, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.provider = provider;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String provider;
        private String username;
        private String email;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public Builder() {
            this.password = UUID.randomUUID().toString();
        }

        public OidcUserDto build(){
            return new OidcUserDto(provider, username, email, password, authorities);
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder authorities(List<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }
    }
}
