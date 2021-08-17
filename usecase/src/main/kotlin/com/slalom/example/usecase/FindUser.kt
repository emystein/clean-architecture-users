package com.slalom.example.usecase

import com.slalom.example.domain.entity.User
import com.slalom.example.usecase.port.UserRepository
import java.util.*

class FindUser(private val repository: UserRepository) {
    fun findById(id: String?): Optional<User> {
        return repository.findById(id)
    }

    fun findAllUsers(): List<User?>? {
        return repository.findAllUsers()
    }
}