package com.recipe.universe.domain.user.service;

import com.recipe.universe.domain.user.dto.UserDto;
import com.recipe.universe.domain.user.entity.User;
import com.recipe.universe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(String userId, String pwd){
        String encodedPwd = passwordEncoder.encode(pwd);
        User user = User.builder()
                .userId(userId)
                .pwd(encodedPwd)
                .build();
        Long id = userRepository.save(user).getId();
        return id;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUserId(username);
    }

    public Collection<? extends GrantedAuthority> loadUserRoleByUsername(String username){
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Transactional
    public UserDto save(String username, String password, String provider, String email){
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .userId(username)
                .pwd(encodedPassword)
                .provider(provider)
                .email(email)
                .build();
        Long id = userRepository.save(user).getId();
        return UserDto.convert(id, user);
    }

    public UserDto findByUsername(String username){
        User user = userRepository.findByUserId(username).orElseThrow();
        return UserDto.convert(user);
    }

}
