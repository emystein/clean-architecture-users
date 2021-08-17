package com.slalom.example.controller

import com.slalom.example.controller.model.UserWeb
import com.slalom.example.usecase.CreateUser
import com.slalom.example.usecase.FindUser
import com.slalom.example.usecase.LoginUser

class UserController(
    private val createUser: CreateUser,
    private val findUser: FindUser,
    private val loginUser: LoginUser
) {
    fun createUser(userWeb: UserWeb): UserWeb {
        val user = userWeb.toUser()
        return UserWeb.toUserWeb(createUser.create(user))
    }

    fun login(email: String?, password: String?): UserWeb {
        return UserWeb.toUserWeb(loginUser.login(email!!, password!!))
    }

    fun getUser(userId: String?): UserWeb {
        return UserWeb.toUserWeb(
            findUser.findById(userId)!!.orElseThrow { RuntimeException("user not found") })
    }

    fun allUsers(): List<UserWeb> {
        return findUser.findAllUsers()!!.map { user -> UserWeb.toUserWeb(user) }
    }
}