package com.recipe.universe.domain.user.service;

import com.recipe.universe.domain.user.entity.User;
import com.recipe.universe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
