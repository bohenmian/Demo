package com.example.user.enums

enum class MessageEnum(val code: Int, val message: String) {
    CREATE_USER_SUCCESS(10000, "create user success"),
    USER_NOT_EXIST(10001, "user not exist"),
    LOGIN_SUCCESS(10002, "login success"),
    LOGIN_FAIL(10003, "login fail")
}
