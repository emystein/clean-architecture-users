package com.slalom.example.usecase

import com.slalom.example.domain.entity.User
import com.slalom.example.domain.entity.User.Companion.builder
import com.slalom.example.usecase.exception.UserAlreadyExistsException
import com.slalom.example.usecase.port.IdGenerator
import com.slalom.example.usecase.port.PasswordEncoder
import com.slalom.example.usecase.port.UserRepository
import com.slalom.example.usecase.validator.UserValidator

class CreateUser(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val idGenerator: IdGenerator
) {
    fun create(user: User): User? {
        UserValidator.validateCreateUser(user)
        if (repository.findByEmail(user.email).isPresent) {
            throw UserAlreadyExistsException(user.email)
        }
        val userToSave = builder()
            .id(idGenerator.generate())
            .email(user.email)
            .password(passwordEncoder.encode(user.email + user.password))
            .lastName(user.lastName)
            .firstName(user.firstName)
            .build()
        return repository.create(userToSave)
    }
}