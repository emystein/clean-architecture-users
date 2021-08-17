package com.slalom.example.usecase.exception

class UserAlreadyExistsException(email: String?) : RuntimeException(email)