package com.slalom.example.usecase.port

import com.slalom.example.domain.entity.User
import java.util.*

interface UserRepository {
    fun create(user: User?): User?
    fun findById(id: String?): Optional<User>
    fun findByEmail(email: String?): Optional<User>
    fun findAllUsers(): List<User?>?
}