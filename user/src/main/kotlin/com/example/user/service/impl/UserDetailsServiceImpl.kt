package com.example.user.service.impl

import com.example.user.exception.UserNotExistException
import com.example.user.repository.UserRepository
import com.example.user.security.entity.JwtUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val saveUser = userRepository.findByUsername(username) ?: throw UserNotExistException("user not found")
        return JwtUser(saveUser)
    }
}
