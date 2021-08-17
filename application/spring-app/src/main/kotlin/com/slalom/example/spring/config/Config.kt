package com.slalom.example.spring.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.slalom.config.SpringConfig
import com.slalom.example.controller.UserController
import com.slalom.example.usecase.CreateUser
import com.slalom.example.usecase.FindUser
import com.slalom.example.usecase.LoginUser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class Config {
    private val config = SpringConfig()
    @Bean
    open fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return objectMapper
    }

    @Bean
    open fun createUser(): CreateUser {
        return config.createUser()
    }

    @Bean
    open fun findUser(): FindUser {
        return config.findUser()
    }

    @Bean
    open fun loginUser(): LoginUser {
        return config.loginUser()
    }

    @Bean
    open fun userController(): UserController {
        return UserController(createUser(), findUser(), loginUser())
    }
}