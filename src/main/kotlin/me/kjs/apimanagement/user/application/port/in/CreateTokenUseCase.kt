package me.kjs.apimanagement.user.application.port.`in`


interface CreateTokenUseCase {
	fun createToken(createRequest: UserAuthForm.Create.Request): UserAuthForm.Token.Response
}