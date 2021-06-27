package me.kjs.apimanagement.user.application.service

import me.kjs.apimanagement.user.application.port.`in`.GetUserUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserForm
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import org.springframework.stereotype.Service

@Service
class GetUserService(
	private val findUserPort: FindUserPort
) : GetUserUseCase {
	override fun getUser(userId: String): UserForm.Find.Response.One {
		val user = findUserPort.findUser(userId) ?: TODO()
		return UserForm.Find.Response.One(
			user.email,
			user.name
		)
	}
}