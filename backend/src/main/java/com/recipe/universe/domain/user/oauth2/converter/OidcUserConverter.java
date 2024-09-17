package com.recipe.universe.domain.user.oauth2.converter;

import com.recipe.universe.domain.user.oauth2.dto.OidcUserDto;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

/**
 * 서로 형식의 응답을 하는 OAUTH2 Provider들의 응답을 하나의 통합된 DTO로 변환하는 역할
 * Component어노테이션을 사용해서 자동으로 주입
 */
@Component
public interface OidcUserConverter {
    public String getProviderName();
    public default boolean supports(OidcUser oidcUser, ClientRegistration clientRegistration){
        return getProviderName().equals(clientRegistration.getRegistrationId());
    }
    public OidcUserDto convert(OidcUser oidcUser, ClientRegistration clientRegistration);
}
