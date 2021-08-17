package com.slalom.config

import com.slalom.example.usecase.port.UserRepository
import com.slalom.example.db.InMemoryUserRepository
import com.slalom.example.jug.JugIdGenerator
import com.slalom.example.usecase.port.PasswordEncoder
import com.slalom.example.encoder.Sha256PasswordEncoder
import com.slalom.example.usecase.CreateUser
import com.slalom.example.usecase.FindUser
import com.slalom.example.usecase.LoginUser
import com.slalom.example.usecase.port.IdGenerator

class ManualConfig {
    private val userRepository: UserRepository = InMemoryUserRepository()
    private val idGenerator: IdGenerator = JugIdGenerator()
    private val passwordEncoder: PasswordEncoder = Sha256PasswordEncoder()
    fun createUser(): CreateUser {
        return CreateUser(userRepository, passwordEncoder, idGenerator)
    }

    fun findUser(): FindUser {
        return FindUser(userRepository)
    }

    fun loginUser(): LoginUser {
        return LoginUser(userRepository, passwordEncoder)
    }
}