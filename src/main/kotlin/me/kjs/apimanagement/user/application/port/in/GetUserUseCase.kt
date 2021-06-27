package me.kjs.apimanagement.user.application.port.`in`

interface GetUserUseCase {
	fun getUser(userId: String): UserForm.Find.Response.One
}
