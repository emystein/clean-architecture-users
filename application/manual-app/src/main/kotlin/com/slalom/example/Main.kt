package com.slalom.example

import com.slalom.config.ManualConfig
import com.slalom.example.domain.entity.User.Companion.builder

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        // Setup
        val config = ManualConfig()
        val createUser = config.createUser()
        val findUser = config.findUser()
        val loginUser = config.loginUser()
        val user = builder()
            .email("john.doe@gmail.com")
            .password("mypassword")
            .lastName("doe")
            .firstName("john")
            .build()

        // Create a user
        val actualCreateUser = createUser.create(user)
        println("User created with id " + actualCreateUser!!.id)

        // Find a user by id
        val actualFindUser = findUser.findById(
            actualCreateUser.id
        )
        println("Found user with id " + actualFindUser.get().id)

        // List all users
        val users = findUser.findAllUsers()
        println("List all users: $users")

        // Login
        loginUser.login("john.doe@gmail.com", "mypassword")
        println("Allowed to login with email 'john.doe@gmail.com' and password  'mypassword'")
    }
}