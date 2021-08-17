package com.slalom.example.db.hazelcast.model

import com.slalom.example.domain.entity.User
import com.slalom.example.domain.entity.User.Companion.builder
import java.io.Serializable

class UserDb : Serializable {
    var id: String? = null
    var email: String? = null
    var password: String? = null
    var lastName: String? = null
    var firstName: String? = null
    var role: String? = null
    fun toUser(): User {
        return builder()
            .id(id)
            .email(email)
            .password(password)
            .lastName(lastName)
            .firstName(firstName)
            .build()
    }

    companion object {
        private const val serialVersionUID = 1L
        fun from(user: User?): UserDb {
            val userDb = UserDb()
            userDb.id = user!!.id
            userDb.email = user.email
            userDb.password = user.password
            userDb.firstName = user.firstName
            userDb.lastName = user.lastName
            return userDb
        }
    }
}