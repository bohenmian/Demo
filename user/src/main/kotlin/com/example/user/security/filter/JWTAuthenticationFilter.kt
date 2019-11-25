package com.example.user.security.filter

import com.example.user.http.request.LoginRequest
import com.example.user.security.constant.SecurityConstants
import com.example.user.security.entity.JwtUser
import com.example.user.utils.AuthorizationUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    private var manager: AuthenticationManager? = null

    init {
        super.setFilterProcessesUrl("/auth/user/login")
        manager = authenticationManager
    }

    private val objectMapper = ObjectMapper()

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse?): Authentication? {
        return try {
            val (username, password) = objectMapper.readValue(request.inputStream, LoginRequest::class.java)
            val authRequest = UsernamePasswordAuthenticationToken(username, password)
            manager?.authenticate(authRequest)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val jwtUser = authResult.principal as JwtUser
        val roles = jwtUser.authorities
                .stream()
                .map { it.authority }
                .collect(Collectors.toList())
        val token = AuthorizationUtil.createToken(jwtUser.username, roles)
        response.setHeader(SecurityConstants.TOKEN_HEADER, token)
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.message)
    }
}
