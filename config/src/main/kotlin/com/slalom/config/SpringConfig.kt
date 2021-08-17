package com.slalom.config

import com.slalom.example.usecase.port.UserRepository
import com.slalom.example.usecase.port.PasswordEncoder
import com.slalom.example.encoder.Sha256PasswordEncoder
import com.slalom.example.usecase.CreateUser
import com.slalom.example.usecase.FindUser
import com.slalom.example.usecase.LoginUser
import com.slalom.example.db.hazelcast.HazelcastUserRepository
import com.slalom.example.uuid.UuidGenerator

class SpringConfig {
    private val userRepository: UserRepository = HazelcastUserRepository()
    private val passwordEncoder: PasswordEncoder = Sha256PasswordEncoder()
    fun createUser(): CreateUser {
        return CreateUser(userRepository, passwordEncoder, UuidGenerator())
    }

    fun findUser(): FindUser {
        return FindUser(userRepository)
    }

    fun loginUser(): LoginUser {
        return LoginUser(userRepository, passwordEncoder)
    }
}