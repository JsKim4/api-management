package me.kjs.apimanagement.user.domain

import java.time.LocalDateTime

class UserAuth(
	private val user: User,
	private val clientId: String,
	refreshToken: String,
	expiredDateTime: LocalDateTime,
) {
	private var tokenWithEffectiveTime: TokenWithEffectiveTime = TokenWithEffectiveTime(refreshToken, expiredDateTime)

	class TokenWithEffectiveTime(
		private val refreshToken: String,
		private val expiredDateTime: LocalDateTime,
	) {
		fun validRefreshToken(refreshToken: String): Boolean {
			return this.refreshToken == refreshToken && expiredDateTime.isAfter(LocalDateTime.now())
		}
	}

	fun equalsClientId(clientId: String): Boolean {
		return this.clientId == clientId
	}

	fun update(refreshToken: String, expiredDateTime: LocalDateTime) {
		this.tokenWithEffectiveTime = TokenWithEffectiveTime(refreshToken, expiredDateTime)
	}

	fun validRefreshToken(refreshToken: String): Boolean {
		return tokenWithEffectiveTime.validRefreshToken(refreshToken)
	}
}
