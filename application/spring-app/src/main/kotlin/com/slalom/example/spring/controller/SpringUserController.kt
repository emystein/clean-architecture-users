package com.slalom.example.spring.controller

import com.slalom.example.controller.UserController
import com.slalom.example.controller.model.UserWeb
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class SpringUserController @Autowired constructor(private val controller: UserController) {
    @RequestMapping(value = ["/users"], method = [RequestMethod.POST])
    fun createUser(@RequestBody userWeb: UserWeb?): UserWeb {
        return controller.createUser(userWeb!!)
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun login(@RequestParam("email") email: String?, @RequestParam("password") password: String?): UserWeb {
        return controller.login(email, password)
    }

    @RequestMapping(value = ["/users/{userId}"], method = [RequestMethod.GET])
    fun getUser(@PathVariable("userId") userId: String?): UserWeb {
        return controller.getUser(userId)
    }

    @RequestMapping(value = ["/users"], method = [RequestMethod.GET])
    fun allUsers(): List<UserWeb> {
        return controller.allUsers()
    }
}