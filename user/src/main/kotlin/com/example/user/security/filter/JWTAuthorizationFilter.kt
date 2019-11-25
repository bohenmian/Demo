package com.example.user.security.filter

import com.example.user.security.constant.SecurityConstants
import com.example.user.utils.AuthorizationUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.StringUtils
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader(SecurityConstants.TOKEN_HEADER)
        if (authorization == null || !authorization.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }
        SecurityContextHolder.getContext().authentication = getAuthentication(authorization)
        super.doFilterInternal(request, response, chain)
    }

    private fun getAuthentication(authorization: String): Authentication? {
        val token = authorization.replace(SecurityConstants.TOKEN_PREFIX, "")
        print(token)
        val username = AuthorizationUtil.getUsername(token)
        val roles = AuthorizationUtil.getUseRole(token)
        return if (!StringUtils.isEmpty(username)) {
            UsernamePasswordAuthenticationToken(username, null, roles)
        } else null
    }


}
