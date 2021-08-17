package com.slalom.example.jug

import com.fasterxml.uuid.EthernetAddress
import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.NoArgGenerator
import com.slalom.example.usecase.port.IdGenerator

class JugIdGenerator : IdGenerator {
    override fun generate(): String? {
        return generator().generate().toString().replace("-".toRegex(), "")
    }

    companion object {
        private fun generator(): NoArgGenerator {
            return Generators.timeBasedGenerator(EthernetAddress.fromInterface())
        }
    }
}