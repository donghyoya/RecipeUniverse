package com.recipe.universe.domain.user.user.service;

import com.recipe.universe.domain.user.history.dto.UserHistoryDto;
import com.recipe.universe.domain.user.history.entity.UserHistory;
import com.recipe.universe.domain.user.history.service.UserHistoryService;
import com.recipe.universe.domain.user.role.entity.RoleName;
import com.recipe.universe.domain.user.role.repository.UserRoleRepository;
import com.recipe.universe.domain.user.role.service.RoleService;
import com.recipe.universe.domain.user.user.dto.UserAndRoleDto;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    @Value("${jwt.secret}") private String forCheater;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserHistoryService historyService;

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

    public Collection<? extends GrantedAuthority> loadUserRoleByUsername(Long userId){
        return roleService.loadUserRoleByUserId(userId).stream().map(SimpleGrantedAuthority::new).toList();
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

    @Transactional
    public UserDto updateNickname(Long userId, String nickname){
        User user = userRepository.findById(userId).orElseThrow();
        user.updateNickname(nickname);
        return UserDto.convert(user);
    }

    /**
     * 개발용 치트유저
     * @return 치트유저
     */
    @Transactional
    public UserDto findCheatUser(){
        Optional<User> optionalUser = userRepository.findByUserIdAndProvider("cheat", "cheat");
        UserDto user;
        if(optionalUser.isEmpty()){
            user = this.save("cheat", forCheater, "cheat", "cheat");
            addUserRole(user.getId(), RoleName.ROLE_ADMIN);
        }else {
            user = UserDto.convert(optionalUser.get());
        }
        return user;
    }

    @Transactional
    public UserDto userLogin(String username){
        User user = userRepository.findByUserId(username).orElseThrow();
        historyService.createUserHistory(user);
        return UserDto.convert(user);
    }

    public Page<UserDto> findAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).map(UserDto::convert);
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

    public Page<UserHistoryDto> findUserHistoryById(Long id, int page, int size){
        return historyService.findUserHistoryByUserId(id,page,size);
    }
}
