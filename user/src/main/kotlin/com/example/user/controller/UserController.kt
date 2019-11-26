package com.example.user.controller

import com.example.user.exception.UserNotExistException
import com.example.user.http.request.CreateUserRequest
import com.example.user.http.response.SuccessMessage
import com.example.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class UserController(val userService: UserService) {

    @PostMapping("/user/create")
    fun signUp(@RequestBody @Valid request: CreateUserRequest): ResponseEntity<SuccessMessage> =
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userService.createUser(request))

    @GetMapping("/user/hello")
    fun hello(): String {
        return "hello"
    }

    @ExceptionHandler(UserNotExistException::class)
    fun handleUserNotExistException(e: UserNotExistException) =
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.message)

}
