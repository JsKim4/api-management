package me.kjs.apimanagement.user.application.port.`in`

interface UserAuthForm {
	interface Create {
		data class Request(
			val email: String,
			val password: String,
			val clientId: String,
		)
	}

	interface Token {
		data class Response(
			val accessToken: String,
			val refreshToken: String,
			val accessTokenExpiredSecond: Long,
			val refreshTokenExpiredSecond: Long,
		)
	}

	interface Refresh {
		class Request(
			val userId: String,
			val clientId: String,
			val refreshToken: String
		)
	}
}