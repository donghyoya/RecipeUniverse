package com.recipe.universe.domain.user.oauth2.service;

import com.recipe.universe.domain.user.oauth2.converter.OidcUserConverter;
import com.recipe.universe.domain.user.oauth2.dto.OidcUserDto;
import com.recipe.universe.domain.user.oauth2.exception.UnSupportedProviderException;
import com.recipe.universe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomOidcService extends OidcUserService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final List<OidcUserConverter> converters;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oAuth2User = super.loadUser(userRequest);
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        Optional<OidcUserConverter> converter = converters.stream().filter(
                cv->cv.supports(oAuth2User, clientRegistration)
        ).findFirst();

        if(converter.isEmpty()){
            throw new UnSupportedProviderException(clientRegistration.getRegistrationId());
        }
        OidcUserDto user = converter.get().convert(oAuth2User, clientRegistration);
        if(!userService.existsByUsername(user.getUsername())){
            userService.save(user.getUsername(),user.getPassword(), user.getProvider(), user.getEmail());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        Collection<? extends GrantedAuthority> userAuthorities = userDetails.getAuthorities();
        Collection<? extends GrantedAuthority> oidcAuthorities = user.getAuthorities();
        Collection<GrantedAuthority> combinedAuthorities = new ArrayList<>();
        combinedAuthorities.addAll(userAuthorities);
        combinedAuthorities.addAll(oidcAuthorities);

        return new DefaultOidcUser(
                combinedAuthorities,
                oAuth2User.getIdToken()
        );
    }
}
