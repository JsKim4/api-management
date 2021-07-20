package me.kjs.apimanagement.user.application.service

import me.kjs.apimanagement.user.application.port.`in`.WithdrawUserUseCase
import me.kjs.apimanagement.user.application.port.out.DeleteUserPort
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import org.springframework.stereotype.Service


@Service
class DeleteUserService(
	private val deleteUserPort: DeleteUserPort,
	private val findUserPort: FindUserPort,
) : WithdrawUserUseCase {

	override fun withdrawUser(userId: String) {
		val findUser = findUserPort.findUser(userId) ?: throw Exception()
		deleteUserPort.deleteUser(findUser)
	}
}