package me.kjs.apimanagement.applications.application.port.`in`

interface SecretKeyRefreshUseCase {
	fun refreshKeyById(applicationId: String, userId: String)

}
