package me.kjs.apimanagement.user.application.port.`in`


interface CreateUserUseCase {
	fun createUser(createRequest: UserForm.Create.Request): UserForm.Create.Response
}