package com.example.user.http.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class CreateUserRequest(

        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9]+$")
        @Size(min = 6, max = 18)
        val username: String,

        @NotNull
        val password: String,

        @NotNull
        @Email
        val email: String,

        @NotNull
        @Size(min = 11, max = 11)
        val cellphone: Long

)
