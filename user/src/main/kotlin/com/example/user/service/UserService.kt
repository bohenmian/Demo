package com.example.user.service

import com.example.user.http.request.CreateUserRequest
import com.example.user.http.request.LoginRequest
import com.example.user.http.response.SuccessMessage

interface UserService {

    fun createUser(request: CreateUserRequest): SuccessMessage

    fun login(request: LoginRequest): SuccessMessage
}
