package com.recipe.universe.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 그냥 인증되었는지 테스트하기 위한 (개발과정에서나 쓸 것)
 */
@Tag(name = "인증 테스트", description = "개발용, JWT가 제대로 작동하는지 검사할 때 사용할 것")
@RestController
public class IsAuthenticatedController {
    @Operation(
            summary = "JWT 인증 확인",
            description = "소셜 로그인이 제대로 작동하는 지 확인하기 위한 개발용 API",
            parameters = {
                    @Parameter(
                            name = "인증헤더",
                            description = "Bearer 형식의 JWT 토큰을 포함한 Authorization 헤더",
                            required = true,
                            in = ParameterIn.HEADER,
                            example = "Bearer {YOUR JWT TOKEN}"
                    )
            },
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
    @GetMapping("/isAuthenticated")
    public String isAuthenticated(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException();
        }
        return "OK";
    }
}
