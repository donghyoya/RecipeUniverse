package com.recipe.universe.domain.user.role.service;

import com.recipe.universe.domain.user.role.entity.Role;
import com.recipe.universe.domain.user.role.entity.RoleName;
import com.recipe.universe.domain.user.role.entity.UserRole;
import com.recipe.universe.domain.user.role.repository.RoleRepository;
import com.recipe.universe.domain.user.role.repository.UserRoleRepository;
import com.recipe.universe.domain.user.user.entity.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public Long createRole(RoleName roleName){
        return createRole(roleName.getRoleName(), roleName.getExpression());
    }

    @Transactional
    public Long createRole(String roleName, String expression){
        Role role = new Role(roleName, expression);
        Long id = roleRepository.save(role).getId();
        return id;
    }

    @Transactional
    public Long addUserRole(User user, RoleName roleName){
        Optional<Role> role = roleRepository.findByRoleName(roleName.getRoleName());
        if(role.isEmpty()){
            createRoles();
            role = roleRepository.findByRoleName(roleName.getRoleName());
        }
        UserRole userRole = new UserRole(user, role.get());
        return userRoleRepository.save(userRole).getId();
    }

    public List<String> loadUserRoleByUserId(Long userId){
        return userRoleRepository.findByUserId(userId).stream().map(userRole->userRole.getRole().getRoleName()).toList();
    }

    @Transactional
    public void deleteUserRole(Long userId, RoleName roleName){
        Role role = roleRepository.findByRoleName(roleName.getRoleName()).orElseThrow();
        Optional<UserRole> userRole = userRoleRepository.findByUserIdAndRoleId(userId, role.getId());
        userRole.ifPresent(userRoleRepository::delete);
    }

    /**
     * 개발중에 사용할 것. Role이 사전에 생성되지 않았으면 대신 만들어주는 코드
     */
    private void createRoles(){
        RoleName[] roleNames = RoleName.values();
        for(RoleName roleName : roleNames){
            createRole(roleName);
        }
    }
}
