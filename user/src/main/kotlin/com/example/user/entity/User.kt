package com.example.user.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, length = 25)
        val username: String,

        @Column(nullable = false, length = 255)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        val password: String,

        @Column(nullable = false)
        val email: String,

        @Column(nullable = false)
        val cellPhone: Long,

        @Column(nullable = false)
        val roles: String

) {
    fun getRoles(): List<SimpleGrantedAuthority> {
        val authorities = mutableListOf<SimpleGrantedAuthority>()
        roles.split(",").forEach { authorities.add(SimpleGrantedAuthority("ROLE_$it")) }
        return authorities
    }
}
