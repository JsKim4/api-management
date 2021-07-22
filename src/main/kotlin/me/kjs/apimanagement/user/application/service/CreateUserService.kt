package me.kjs.apimanagement.user.application.service

import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.user.application.port.`in`.CreateUserUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserForm
import me.kjs.apimanagement.user.application.port.out.FindEmailPort
import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.application.port.out.RecordUserPort
import me.kjs.apimanagement.user.domain.core.User
import org.springframework.stereotype.Service

@Service
class CreateUserService(
	private val recordUserPort: RecordUserPort,
	private val findEmailPort: FindEmailPort,
	private val passwordEncoder: PasswordEncoder,
	private val idGeneratePort: IdGeneratePort,
) : CreateUserUseCase {


	override fun createUser(createRequest: UserForm.Create.Request): UserForm.Create.Response {
		if (findEmailPort.existEmail(createRequest.email)) throw Exception()

		val user = with(createRequest) {
			User.by(idGeneratePort.generateId(), name, email, password, passwordEncoder)
		}
		recordUserPort.recordUser(user)
		return UserForm.Create.Response(
			user.id,
			user.email,
			user.name,
		)
	}
}