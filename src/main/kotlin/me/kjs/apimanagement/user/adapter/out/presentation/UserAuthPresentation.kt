package me.kjs.apimanagement.user.adapter.out.presentation

import me.kjs.apimanagement.user.application.port.`in`.UserAuthForm


interface UserAuthPresentation {
	interface Create {
		data class Request(
			val email: String,
			val password: String,
			val clientId: String,
		) {
			fun toForm(): UserAuthForm.Create.Request = UserAuthForm.Create.Request(email, password, clientId)
		}

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
		) {
			fun toFormWith(userId: String) = UserAuthForm.Refresh.Request(userId, clientId, refreshToken)
		}
	}

}
