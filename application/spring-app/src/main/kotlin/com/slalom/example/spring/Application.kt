package com.slalom.example.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(value = ["com.slalom.example"])
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}