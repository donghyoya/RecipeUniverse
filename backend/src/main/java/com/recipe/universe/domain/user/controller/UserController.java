package com.recipe.universe.domain.user.controller;
import com.recipe.universe.domain.user.controller.form.AddUserRoleForm;
import com.recipe.universe.domain.user.role.service.RoleService;
import com.recipe.universe.domain.user.user.dto.UserAndRoleDto;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.service.UserService;
import com.recipe.universe.global.dto.BaseListResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "JWT")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public BaseListResponse<UserDto> getUsers(){
        return new BaseListResponse<>(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserAndRoleDto getUserRole(
            @PathVariable("id") Long id
    ){
        return userService.findUserByUserId(id);
    }

    @PostMapping("/{id}/roles")
    public UserAndRoleDto addUserRole(
            @PathVariable("id") Long id,
            @RequestBody AddUserRoleForm form
    ){
        userService.addUserRole(id, form.getRoleName());
        return userService.findUserByUserId(id);
    }

    @PostMapping("/{id}/roles/delete")
    public UserAndRoleDto deleteUserRole(
            @PathVariable("id") Long id,
            @RequestBody AddUserRoleForm form
    ){
        roleService.deleteUserRole(id, form.getRoleName());
        return userService.findUserByUserId(id);
    }
}
