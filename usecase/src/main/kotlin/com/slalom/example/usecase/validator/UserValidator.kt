package com.slalom.example.usecase.validator

import com.slalom.example.domain.entity.User
import com.slalom.example.usecase.exception.UserValidationException
import org.apache.commons.lang3.StringUtils

object UserValidator {
    fun validateCreateUser(user: User?) {
        if (user == null) throw UserValidationException("User should not be null")
        if (StringUtils.isBlank(user.email)) throw UserValidationException("Email should not be null")
        if (StringUtils.isBlank(user.firstName)) throw UserValidationException("First name should not be null")
        if (StringUtils.isBlank(user.lastName)) throw UserValidationException("Last name should not be null")
    }
}