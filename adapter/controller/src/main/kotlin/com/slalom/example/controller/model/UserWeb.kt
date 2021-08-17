package com.slalom.example.controller.model

import com.slalom.example.domain.entity.User.Companion.builder
import com.slalom.example.domain.entity.User

class UserWeb {
    var id: String? = null
    var email: String? = null
    var password: String? = null
    var lastName: String? = null
    var firstName: String? = null
    fun toUser(): User {
        return builder()
            .email(email)
            .password(password)
            .lastName(lastName)
            .firstName(firstName)
            .build()
    }

    companion object {
        fun toUserWeb(user: User?): UserWeb {
            val userWeb = UserWeb()
            userWeb.id = user!!.id
            userWeb.email = user.email
            // do not map password
            userWeb.lastName = user.lastName
            userWeb.firstName = user.firstName
            return userWeb
        }
    }
}