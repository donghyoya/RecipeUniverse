package com.recipe.universe.domain.user.oauth2.converter;

import com.recipe.universe.domain.user.oauth2.dto.OidcUserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GoogleOidcUserConverter implements OidcUserConverter{
    private final String providerName = "google";

    @Override
    public String getProviderName() {
        return this.providerName;
    }

    @Override
    public OidcUserDto convert(OidcUser oidcUser, ClientRegistration clientRegistration) {
        return OidcUserDto.builder()
                .username(oidcUser.getAttribute("email"))
                .provider(clientRegistration.getRegistrationId())
                .password(UUID.randomUUID().toString())
                .email(oidcUser.getAttribute("email"))
                .authorities(oidcUser.getAuthorities().stream().toList())
                .build();
    }
}
