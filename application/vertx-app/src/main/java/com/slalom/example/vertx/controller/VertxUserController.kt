package com.slalom.example.vertx.controller

import com.slalom.example.controller.UserController
import com.slalom.example.controller.model.UserWeb
import com.slalom.example.vertx.utils.JsonCollectors
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext

class VertxUserController(private val controller: UserController) {
    fun createUser(routingContext: RoutingContext) {
        val response = routingContext.response()
        val body = routingContext.body
        if (isNull(body)) {
            sendError(400, response)
        } else {
            val userWeb = body.toJsonObject().mapTo(UserWeb::class.java)
            val user = controller.createUser(userWeb)
            val result = JsonObject.mapFrom(user)
            sendSuccess(result, response)
        }
    }

    fun login(routingContext: RoutingContext) {
        val response = routingContext.response()
        val email = routingContext.request().getParam("email")
        val password = routingContext.request().getParam("password")
        if (email == null || password == null) {
            sendError(400, response)
        } else {
            val user = controller.login(email, password)
            val result = JsonObject.mapFrom(user)
            sendSuccess(result, response)
        }
    }

    fun findUser(routingContext: RoutingContext) {
        val response = routingContext.response()
        val userId = routingContext.request().getParam("userId")
        if (userId == null) {
            sendError(400, response)
        } else {
            try {
                val user = controller.getUser(userId)
                val result = JsonObject.mapFrom(user)
                sendSuccess(result, response)
            } catch (e: RuntimeException) {
                sendError(404, response)
            }
        }
    }

    fun findAllUser(routingContext: RoutingContext) {
        val response = routingContext.response()
        val users = controller.allUsers()
        val result = users.stream()
            .map { obj: UserWeb? -> JsonObject.mapFrom(obj) }
            .collect(JsonCollectors.toJsonArray())
        response
            .putHeader("content-type", "application/json")
            .end(result!!.encodePrettily())
    }

    private fun isNull(buffer: Buffer?): Boolean {
        return buffer == null || "" == buffer.toString()
    }

    private fun sendError(statusCode: Int, response: HttpServerResponse) {
        response
            .putHeader("content-type", "application/json")
            .setStatusCode(statusCode)
            .end()
    }

    private fun sendSuccess(body: JsonObject, response: HttpServerResponse) {
        response
            .putHeader("content-type", "application/json")
            .end(body.encodePrettily())
    }
}