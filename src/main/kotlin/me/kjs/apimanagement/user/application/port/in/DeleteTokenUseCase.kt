package me.kjs.apimanagement.user.application.port.`in`

interface DeleteTokenUseCase {
	fun deleteToken(userId: String, clientId: String)
}
