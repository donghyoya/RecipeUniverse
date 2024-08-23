package com.recipe.universe.domain.User.service;

import com.recipe.universe.domain.User.entity.User;
import com.recipe.universe.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(SecurityUserService.class);

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("UserId not found: "+userId));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getPwd())
                .authorities("ROLE_USER")
                .build();
    }
}
