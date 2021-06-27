package me.kjs.apimanagement.user.application.port.`in`


interface RefreshTokenUseCase {
	fun refreshToken(refreshRequest: UserAuthForm.Refresh.Request): UserAuthForm.Token.Response
}