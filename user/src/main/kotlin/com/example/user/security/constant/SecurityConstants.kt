package com.example.user.security.constant

class SecurityConstants {

    companion object {
        const val TOKEN_HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        const val SECRET = "U2VjcmV0S2V5VG9HZW5KV1RzQXV0aG9yaXphdGlvblNlY3JldEtleVRvR2VuSldUc0JlYXJlclNlY3JldEtleVRvR2VuSldUcw=="
        const val ISS: String = "SecretKey"
        const val EXPIRATION = 24 * 60 * 60 * 1000
    }


}
