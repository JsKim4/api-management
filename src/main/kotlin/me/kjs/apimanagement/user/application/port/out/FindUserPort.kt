package me.kjs.apimanagement.user.application.port.out

import me.kjs.apimanagement.user.domain.core.User

interface FindUserPort {
	fun findUser(userId: String): User?
	fun findUserByEmail(email: String): User?
}