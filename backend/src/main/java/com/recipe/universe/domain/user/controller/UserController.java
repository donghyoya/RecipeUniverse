package com.recipe.universe.domain.user.controller;
import com.recipe.universe.domain.user.user.dto.UserAndRoleDto;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.service.UserService;
import com.recipe.universe.global.dto.BaseListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

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
}
