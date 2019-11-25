package com.example.user.security.entity

import com.example.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser : UserDetails {
    private val id: Long?
    private val username: String
    private val password: String
    private val authorities: Collection<GrantedAuthority>

    constructor(user: User) {
        id = user.id
        username = user.username
        password = user.password
        authorities = user.getRoles()
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override/**/ fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


}
