package com.slalom.example.vertx.utils

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import java.util.*
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Collector.Characteristics

object JsonCollectors {
    fun toJsonArray(): Collector<JsonObject, MutableList<JsonObject>, JsonArray> {
        return object : Collector<JsonObject, MutableList<JsonObject>, JsonArray> {
            override fun supplier(): Supplier<MutableList<JsonObject>> {
                return Supplier { ArrayList() }
            }

            override fun accumulator(): BiConsumer<MutableList<JsonObject>, JsonObject> {
                return BiConsumer { obj: MutableList<JsonObject>, e: JsonObject -> obj.add(e) }
            }

            override fun combiner(): BinaryOperator<MutableList<JsonObject>> {
                return BinaryOperator { left: MutableList<JsonObject>, right: List<JsonObject>? ->
                    left.addAll(
                        right!!
                    )
                    left
                }
            }

            override fun finisher(): Function<MutableList<JsonObject>, JsonArray> {
                return Function { list: List<JsonObject>? -> JsonArray(list) }
            }

            override fun characteristics(): Set<Characteristics> {
                return EnumSet.of(Characteristics.UNORDERED)
            }
        }
    }
}