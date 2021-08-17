package com.slalom.example.usecase

import com.slalom.example.domain.entity.User
import com.slalom.example.usecase.exception.NotAllowedException
import com.slalom.example.usecase.port.PasswordEncoder
import com.slalom.example.usecase.port.UserRepository

class LoginUser(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun login(email: String, password: String): User? {
        val user = userRepository.findByEmail(email).orElseThrow { NotAllowedException("Not allowed") }
        val hashedPassword = passwordEncoder.encode(email + password)
        if (user!!.password != hashedPassword) throw NotAllowedException("Not allowed")
        return user
    }
}