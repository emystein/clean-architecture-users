package com.slalom.example.db.hazelcast

import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance

internal object Hazelcast {
    private val LOCK = Any()
    private var HAZELCAST: HazelcastInstance? = null
    val instance: HazelcastInstance?
        get() {
            if (HAZELCAST == null) {
                synchronized(LOCK) {
                    if (HAZELCAST == null) {
                        HAZELCAST = Hazelcast.newHazelcastInstance()
                    }
                }
            }
            return HAZELCAST
        }
}