package com.example.utils

enum class HttpStatus(val code: Int, val description: String) {
    OK(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
    companion object {
        fun fromCode(code: Int): HttpStatus? {
            return entries.find { it.code == code }
        }
    }
}