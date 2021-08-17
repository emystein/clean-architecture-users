package com.slalom.example.domain.entity

class User private constructor(
    val id: String?,
    val email: String?,
    val password: String?,
    val lastName: String?,
    val firstName: String?
) {

    class UserBuilder internal constructor() {
        private var id: String? = null
        private var email: String? = null
        private var password: String? = null
        private var lastName: String? = null
        private var firstName: String? = null
        fun id(id: String?): UserBuilder {
            this.id = id
            return this
        }

        fun email(email: String?): UserBuilder {
            this.email = email
            return this
        }

        fun password(password: String?): UserBuilder {
            this.password = password
            return this
        }

        fun lastName(lastName: String?): UserBuilder {
            this.lastName = lastName
            return this
        }

        fun firstName(firstName: String?): UserBuilder {
            this.firstName = firstName
            return this
        }

        fun build(): User {
            return User(id, email, password, lastName, firstName)
        }
    }

    override fun toString(): String {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}'
    }

    companion object {
        @JvmStatic
		fun builder(): UserBuilder {
            return UserBuilder()
        }
    }
}