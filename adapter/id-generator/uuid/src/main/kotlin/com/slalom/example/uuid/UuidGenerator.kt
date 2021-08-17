package com.slalom.example.uuid

import com.slalom.example.usecase.port.IdGenerator
import java.util.UUID

class UuidGenerator : IdGenerator {
    override fun generate(): String? {
        return UUID.randomUUID().toString()
    }
}