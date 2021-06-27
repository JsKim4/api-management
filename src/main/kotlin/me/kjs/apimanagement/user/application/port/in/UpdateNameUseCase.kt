package me.kjs.apimanagement.user.application.port.`in`

interface UpdateNameUseCase {
	fun updateName(nameUpdateRequest: UserForm.Update.Request.Name)
}