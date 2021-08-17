package com.slalom.example.usecase.port

interface PasswordEncoder {
    fun encode(str: String?): String?
}