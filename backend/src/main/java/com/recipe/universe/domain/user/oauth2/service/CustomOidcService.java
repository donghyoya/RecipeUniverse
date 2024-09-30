package com.recipe.universe.domain.user.oauth2.service;

import com.recipe.universe.domain.user.role.service.RoleService;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.oauth2.converter.OidcUserConverter;
import com.recipe.universe.domain.user.oauth2.dto.CustomOidcUser;
import com.recipe.universe.domain.user.oauth2.dto.OidcUserDto;
import com.recipe.universe.domain.user.oauth2.exception.UnSupportedProviderException;
import com.recipe.universe.domain.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomOidcService extends OidcUserService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final List<OidcUserConverter> converters;
    private final RoleService roleService;

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
        UserDto userDetails = loadUserDetails(user);

        Collection<? extends GrantedAuthority> grantedAuthorities = loadAuthorities(userDetails, user.getAuthorities());

        return new CustomOidcUser(
                grantedAuthorities,
                oAuth2User.getIdToken(),
                userDetails
        );
    }

    private UserDto loadUserDetails(OidcUserDto user){
        if(!userService.existsByUsername(user.getUsername())){
            return userService.save(user.getUsername(),user.getPassword(), user.getProvider(), user.getEmail());
        }else {
            return userService.userLogin(user.getUsername());
        }
    }

    private Collection<? extends GrantedAuthority> loadAuthorities(UserDto userDetails, Collection<? extends GrantedAuthority> oidcAuthorities){
        return roleService.loadUserRoleByUserId(userDetails.getId()).stream().map(role->new SimpleGrantedAuthority(role)).toList();
    }
}
