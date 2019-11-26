package com.example.user.controller

import com.example.user.http.request.CreateUserRequest
import com.example.user.http.request.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun should_return_success_when_give_a_valid_user() {
        val createUserRequest = CreateUserRequest("tom", "pass", "hello@gmail.com", 1231231234)
        createUser(createUserRequest)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.code").value(10000))
                .andExpect(jsonPath("$.message").value("create user success"))
    }

    @Test
    internal fun should_login_success_when_username_and_password_correct() {
        val createUserRequest = CreateUserRequest("tom", "pass", "hello@gmail.com", 1231231234)
        createUser(createUserRequest)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.code").value(10000))
                .andExpect(jsonPath("$.message").value("create user success"))
        mockMvc.perform(post("/auth/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(LoginRequest("tom", "pass"))))
                .andExpect(status().isOk)
                .andExpect(header().exists("Authorization"))
    }

    private fun createUser(createUserRequest: CreateUserRequest): ResultActions {
        return mockMvc.perform(post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createUserRequest)))
    }
}
