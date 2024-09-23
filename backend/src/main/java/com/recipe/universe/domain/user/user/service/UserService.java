package com.recipe.universe.domain.user.user.service;

import com.recipe.universe.domain.user.role.entity.RoleName;
import com.recipe.universe.domain.user.role.service.RoleService;
import com.recipe.universe.domain.user.user.dto.UserAndRoleDto;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Transactional
    public Long save(String userId, String pwd){
        String encodedPwd = passwordEncoder.encode(pwd);
        User user = User.builder()
                .userId(userId)
                .pwd(encodedPwd)
                .build();
        roleService.addUserRole(user,RoleName.ROLE_USER);
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
        roleService.addUserRole(user,RoleName.ROLE_USER);
        Long id = userRepository.save(user).getId();
        return UserDto.convert(id, user);
    }

    /**
     * 개발용 치트유저
     * @return 치트유저
     */
    public UserDto findCheatUser(){
        Optional<User> optionalUser = userRepository.findByUserIdAndProvider("cheat", "cheat");
        UserDto user;
        if(optionalUser.isEmpty()){
            user = this.save("cheat", "cheat", "cheat", "cheat");
        }else {
            user = UserDto.convert(optionalUser.get());
        }
        return user;
    }


    public UserDto findByUsername(String username){
        User user = userRepository.findByUserId(username).orElseThrow();
        return UserDto.convert(user);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::convert).toList();
    }

    public UserAndRoleDto findUserByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("UserId not found: " + id));
        List<String> roles = roleService.loadUserRoleByUserId(id);
        return new UserAndRoleDto(user,roles);
    }

    @Transactional
    public void addUserRole(Long id, RoleName roleName){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("UserId not found: " + id));
        roleService.addUserRole(user, roleName);
    }
}
