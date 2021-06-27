package me.kjs.apimanagement.user.adapter.out.presentation


interface UserAuthPresentation {
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
		data class Request(
			val refreshToken: String,
			val clientId: String
		)
	}

}
