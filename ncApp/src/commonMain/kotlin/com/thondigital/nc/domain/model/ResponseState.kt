package com.thondigital.nc.domain.model

enum class ResponseState {
    SUCCESS,
    NOT_FOUND,
    FAILED,
    UNAUTHORIZED;

    companion object {
        fun fromString(string: String): ResponseState {
            return when (string) {
                "SUCCESS" -> SUCCESS
                "NOT_FOUND" -> NOT_FOUND
                "FAILED" -> FAILED
                "UNAUTHORIZED" -> UNAUTHORIZED
                else -> throw IllegalArgumentException("Unknown state: $string")
            }
        }
    }
}
