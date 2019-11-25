package com.example.user.utils

import com.example.user.security.constant.SecurityConstants
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import java.util.stream.Collectors


class AuthorizationUtil {

    companion object {

        fun createToken(username: String, roles: List<String>): String = SecurityConstants.TOKEN_PREFIX + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .setIssuer(SecurityConstants.ISS)
                .setSubject(username)
                .claim("role", roles.joinToString(","))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION))
                .compact()

        fun getUsername(token: String): String = getTokenBody(token).subject


        fun getUseRole(token: String): List<SimpleGrantedAuthority> =
                getTokenBody(token)["role"].toString().split(",")
                        .stream()
                        .map { SimpleGrantedAuthority(it) }
                        .collect(Collectors.toList())


        fun isExpiration(token: String): Boolean = getTokenBody(token).expiration.before(Date())

        private fun getTokenBody(token: String): Claims =
                Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(token)
                        .body
    }

}



