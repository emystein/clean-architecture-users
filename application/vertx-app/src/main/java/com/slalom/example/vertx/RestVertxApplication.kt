package com.slalom.example.vertx

import com.fasterxml.jackson.annotation.JsonInclude
import com.slalom.config.VertxConfig
import com.slalom.example.controller.UserController
import com.slalom.example.vertx.controller.VertxUserController
import io.vertx.core.AbstractVerticle
import io.vertx.core.Launcher
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler

class RestVertxApplication : AbstractVerticle() {
    private val vertxConfig = VertxConfig()
    private val userController =
        UserController(vertxConfig.createUser(), vertxConfig.findUser(), vertxConfig.loginUser())
    private val controller = VertxUserController(userController)
    override fun start() {
        Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.post("/users").handler { routingContext: RoutingContext -> controller.createUser(routingContext) }
        router["/login"].handler { routingContext: RoutingContext -> controller.login(routingContext) }
        router["/users/:userId"].handler { routingContext: RoutingContext -> controller.findUser(routingContext) }
        router["/users"].handler { routingContext: RoutingContext -> controller.findAllUser(routingContext) }
        vertx.createHttpServer().requestHandler { request: HttpServerRequest? -> router.accept(request) }.listen(8080)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Launcher.executeCommand("run", RestVertxApplication::class.java.name)
        }
    }
}