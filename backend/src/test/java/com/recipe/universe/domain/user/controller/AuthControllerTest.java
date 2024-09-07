package com.recipe.universe.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.universe.domain.user.controller.form.UserRegistForm;
import com.recipe.universe.domain.user.jwt.dto.JwtTokenDto;
import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Base64;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class AuthControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private JwtTokenService jwtTokenService;

    private String testUserId = "testUserId";
    private String password = "password";


    @Test
    void regist() throws Exception {
        UserRegistForm form = new UserRegistForm();
        form.setId(testUserId);
        form.setPassword(password);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/auth/regist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Login Success"));

    }

    @Test
    void basicAuthentication() throws Exception {
        String auth = "%s:%s".formatted(testUserId, password);
        String encode = Base64.getEncoder().encodeToString(auth.getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/auth/login")
                .header("Authorization", "Basic %s".formatted(encode))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grantType").value("Bearer"))
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    void refresh() throws Exception {
        JwtTokenDto jwtTokenDto = jwtTokenService.generateToken(testUserId);
        String refreshToken = jwtTokenDto.getRefreshToken();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/auth/refresh")
                        .header("Authorization", "Bearer %s".formatted(refreshToken))
                )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.grantType").value("Bearer"))
                    .andExpect(jsonPath("$.accessToken").exists())
                    .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    void isAuthenticated() throws Exception {
        JwtTokenDto jwtTokenDto = jwtTokenService.generateToken(testUserId);
        String accessToken = jwtTokenDto.getAccessToken();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/isAuthenticated")
                        .header("Authorization", "Bearer %s".formatted(accessToken))
        )
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    void isNotAuthenticated() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/isAuthenticated")
                )
                .andExpect(status().is3xxRedirection()); // loginPage로 리다이렉트하기 떄문
    }

}