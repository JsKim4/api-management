package me.kjs.apimanagement.user.application.service

import me.kjs.apimanagement.user.application.port.`in`.UpdateNameUseCase
import me.kjs.apimanagement.user.application.port.`in`.UpdatePasswordUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserForm
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.application.port.out.RecordUserPort
import org.springframework.stereotype.Service

@Service
class UpdateUserService(
	private val recordUserPort: RecordUserPort,
	private val findUserPort: FindUserPort,
	private val passwordEncoder: PasswordEncoder,
) : UpdateNameUseCase, UpdatePasswordUseCase {
	override fun updateName(nameUpdateRequest: UserForm.Update.Request.Name) {
		val userId = nameUpdateRequest.userId
		val findUser = findUserPort.findUser(userId) ?: TODO()
		findUser.updateName(nameUpdateRequest.name)
		recordUserPort.recordUser(findUser)
	}

	override fun updatePassword(passwordUpdateRequest: UserForm.Update.Request.Password) {
		val userId = passwordUpdateRequest.userId
		val password = passwordUpdateRequest.password
		val findUser = findUserPort.findUser(userId) ?: TODO()
		findUser.updatePassword(password, passwordEncoder)
		recordUserPort.recordUser(findUser)
	}
}