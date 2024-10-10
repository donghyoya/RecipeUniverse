package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 그냥 인증되었는지 테스트하기 위한 (개발과정에서나 쓸 것)
 */
@Tag(name = "인증 테스트", description = "개발용, JWT가 제대로 작동하는지 검사할 때 사용할 것")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class IsAuthenticatedController {
    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    /**
     * 개발용 임시 JWT 토큰 발급기
     * @return JWT 토큰
     */
    @Operation(
            summary = "개발용 임시 JWT 토큰 발급기",
            description = "개발용 임시 JWT 토큰 발급기. 구글 로그인해서 JWT토큰 받는게 귀찮을때 사용할 것"
    )
    @GetMapping("/cheat")
    public String getCheatToken(){
        UserDto cheatUser = userService.findCheatUser();
        return jwtTokenService.generateCheatToken(cheatUser);
    }

    @Operation(
            summary = "JWT 인증 확인",
            description = "소셜 로그인이 제대로 작동하는 지 확인하기 위한 개발용 API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공시",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject(value = "OK")
                            )
                    )
            }
    )
    @SecurityRequirement(name = "JWT")
    @GetMapping("/isAuthenticated")
    public String isAuthenticated(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            return "DENIED";
        }
        return "GRANTED";
    }
}
