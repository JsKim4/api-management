package me.kjs.apimanagement.user.application.port.`in`

interface UpdatePasswordUseCase {
	fun updatePassword(passwordUpdateRequest: UserForm.Update.Request.Password)
}