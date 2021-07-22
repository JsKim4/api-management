package me.kjs.apimanagement.applications.domain.auth

class Application(
	var applicationSecretKey: ApplicationSecretKey,
	var applicationClientId: ApplicationClientId
) {

	fun refreshSecretKey(secretKey: String) {
		applicationSecretKey = ApplicationSecretKey(secretKey)
	}
}