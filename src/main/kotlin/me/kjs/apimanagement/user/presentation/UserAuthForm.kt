package me.kjs.apimanagement.user.presentation

import javax.validation.constraints.NotEmpty

interface UserAuthForm {
	interface Create {
		data class Request(
			@NotEmpty
			val email: String,
			val password: String
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
			val refreshToken: String
		)
	}

}
