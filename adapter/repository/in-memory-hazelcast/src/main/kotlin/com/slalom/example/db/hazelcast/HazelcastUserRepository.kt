package com.slalom.example.db.hazelcast

import com.slalom.example.db.hazelcast.model.UserDb
import com.slalom.example.domain.entity.User
import com.slalom.example.usecase.port.UserRepository
import java.util.*
import java.util.stream.Collectors

class HazelcastUserRepository : UserRepository {
    override fun create(user: User?): User? {
        val userDb: UserDb = UserDb.from(user)
        val map = HAZELCAST!!.getMap<Any?, Any>(MAP_NAME)
        map.put(userDb.id, userDb)
        return user
    }

    override fun findById(id: String?): Optional<User> {
        val map = HAZELCAST!!.getMap<String?, UserDb>(MAP_NAME)
        if (map.containsKey(id)) {
            val userDb = map[id]
            return Optional.of(userDb!!.toUser())
        }
        return Optional.empty()
    }

    override fun findByEmail(email: String?): Optional<User> {
        return HAZELCAST!!.getMap<String, UserDb>(MAP_NAME)
            .values.stream()
            .filter { userDb: UserDb -> userDb.toUser().email == email }
            .map { obj: UserDb -> obj.toUser() }
            .findAny()
    }

    override fun findAllUsers(): List<User?>? {
        return HAZELCAST!!.getMap<String, UserDb>(MAP_NAME)
            .values
            .stream()
            .map { obj: UserDb -> obj.toUser() }
            .collect(Collectors.toList())
    }

    companion object {
        private val HAZELCAST = Hazelcast.instance
        private const val MAP_NAME = "user"
    }
}