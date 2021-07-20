package me.kjs.apimanagement.user.adapter.`in`.infrastructure.presentation

import me.kjs.apimanagement.user.application.port.out.DeleteUserPort
import me.kjs.apimanagement.user.application.port.out.FindEmailPort
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import me.kjs.apimanagement.user.application.port.out.RecordUserPort
import me.kjs.apimanagement.user.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserAdapter : RecordUserPort, FindUserPort, DeleteUserPort, FindEmailPort {

	override fun findUser(userId: String): User? {
		TODO("Not yet implemented")
	}

	override fun findUserByEmail(email: String): User? {
		TODO("Not yet implemented")
	}

	override fun recordUser(user: User) {
		TODO("Not yet implemented")
	}

	override fun deleteUser(user: User) {
		TODO("Not yet implemented")
	}

	override fun existEmail(email: String): Boolean {
		TODO("Not yet implemented")
	}
}