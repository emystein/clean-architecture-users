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

class VertxConfig {
    private val userRepository: UserRepository = InMemoryUserRepository()
    private val idGenerator: IdGenerator = JugIdGenerator()
    private val passwordEncoder: PasswordEncoder = Sha256PasswordEncoder()
    private val createUser = CreateUser(userRepository, passwordEncoder, idGenerator)
    private val findUser = FindUser(userRepository)
    private val loginUser = LoginUser(userRepository, passwordEncoder)
    fun createUser(): CreateUser {
        return createUser
    }

    fun findUser(): FindUser {
        return findUser
    }

    fun loginUser(): LoginUser {
        return loginUser
    }
}