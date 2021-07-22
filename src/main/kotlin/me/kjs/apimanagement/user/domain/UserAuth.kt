package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.user.domain.vo.Token
import java.time.LocalDateTime

class UserAuth(
	private val clientId: String,
	token: Token
) {
	val token: Token
		get() {
			return tokenWithEffectiveTime.token
		}
	private var tokenWithEffectiveTime: TokenWithEffectiveTime =
		TokenWithEffectiveTime(token.value, token.expiredDateTime)

	class TokenWithEffectiveTime(
		private val refreshToken: String,
		private val expiredDateTime: LocalDateTime,
	) {
		val token: Token
			get() = Token(refreshToken, expiredDateTime)

		fun validRefreshToken(refreshToken: String): Boolean {
			return this.refreshToken == refreshToken && expiredDateTime.isAfter(LocalDateTime.now())
		}
	}

	fun equalsClientId(clientId: String): Boolean {
		return this.clientId == clientId
	}

	fun update(token: Token) {
		this.tokenWithEffectiveTime = TokenWithEffectiveTime(token.value, token.expiredDateTime)
	}

	fun validRefreshToken(refreshToken: String): Boolean {
		return tokenWithEffectiveTime.validRefreshToken(refreshToken)
	}
}
