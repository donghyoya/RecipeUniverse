package com.recipe.universe.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.universe.domain.user.controller.form.UserRegistForm;
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
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.grantType").value("Bearer"))
        .andExpect(jsonPath("$.accessToken").exists())
        .andExpect(jsonPath("$.refreshToken").exists());
    }
}