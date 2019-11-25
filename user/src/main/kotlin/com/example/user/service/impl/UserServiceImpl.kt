package com.example.user.service.impl

import com.example.user.entity.User
import com.example.user.enums.MessageEnum
import com.example.user.exception.UserNotExistException
import com.example.user.http.request.CreateUserRequest
import com.example.user.http.request.LoginRequest
import com.example.user.http.response.SuccessMessage
import com.example.user.repository.UserRepository
import com.example.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    private var passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    override fun createUser(request: CreateUserRequest): SuccessMessage {
        val user = User(username = request.username, password = passwordEncoder.encode(request.password),
                email = request.email, cellPhone = request.cellphone, roles = "ROLE_USER")
        userRepository.save(user)
        return SuccessMessage(MessageEnum.CREATE_USER_SUCCESS.code, MessageEnum.CREATE_USER_SUCCESS.message)
    }

    override fun login(request: LoginRequest): SuccessMessage {
        val savedUser = userRepository.findByUsername(request.username)
                ?: throw UserNotExistException(MessageEnum.USER_NOT_EXIST.message)
        val isMatches = passwordEncoder.matches(request.password, savedUser.password)
        return when {
            isMatches -> SuccessMessage(MessageEnum.LOGIN_SUCCESS.code, MessageEnum.LOGIN_SUCCESS.message)
            else -> SuccessMessage(MessageEnum.LOGIN_FAIL.code, MessageEnum.LOGIN_FAIL.message)
        }
    }
}
