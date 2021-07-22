package me.kjs.apimanagement.common

import me.kjs.apimanagement.user.application.port.out.DeleteUserPort
import me.kjs.apimanagement.user.application.port.out.FindEmailPort
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import me.kjs.apimanagement.user.application.port.out.RecordUserPort
import me.kjs.apimanagement.user.domain.core.User

class TestUserAdapter : RecordUserPort, FindUserPort, DeleteUserPort, FindEmailPort {

	private fun getTable(): MutableMap<String, User> {
		return MapDatabase.getTableByName(User::class)
	}

	override fun deleteUser(user: User) {
		getTable().remove(user.id)
	}

	override fun existEmail(email: String): Boolean {
		return getTable().filter {
			it.value.email == email
		}.isNotEmpty()
	}

	override fun findUser(userId: String): User? {
		return getTable()[userId]
	}

	override fun findUserByEmail(email: String): User? {
		val key = getTable().filter { it.value.email == email }.keys.firstOrNull()
		return getTable()[key]
	}

	override fun recordUser(user: User) {
		getTable()[user.id] = user
	}
}