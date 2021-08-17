package com.slalom.example.db

import com.slalom.example.domain.entity.User
import com.slalom.example.usecase.port.UserRepository
import java.util.*

class InMemoryUserRepository : UserRepository {
    private val inMemoryDb: MutableMap<String?, User?> = HashMap()
    override fun create(user: User?): User? {
        inMemoryDb[user!!.id] = user
        return user
    }

    override fun findById(id: String?): Optional<User> {
        return Optional.ofNullable(inMemoryDb[id])
    }

    override fun findByEmail(email: String?): Optional<User> {
        return Optional.ofNullable(
            inMemoryDb.values.firstOrNull { user: User? -> user!!.email == email }
        )
    }

    override fun findAllUsers(): List<User?>? {
        return ArrayList(inMemoryDb.values)
    }
}