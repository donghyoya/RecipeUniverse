package com.recipe.universe.domain.user.controller;

import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class IsAuthenticatedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Test
    void getCheatToken() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/auth/cheat")
                ).andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getContentAsString();
        assertDoesNotThrow(()-> jwtTokenService.validateToken(token));

        mockMvc.perform(
                get("/auth/isAuthenticated")
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andExpect(content().string("GRANTED"));

    }

    @Test
    void noTokenAccess() throws Exception{
        mockMvc.perform(
                get("/auth/isAuthenticated"))
                .andExpect(status().isOk())
                .andExpect(content().string("DENIED"));
    }

}