package me.kjs.apimanagement.user.adapter.`in`.infrastructure.presentation

import me.kjs.apimanagement.user.application.port.out.TokenCreatePort
import me.kjs.apimanagement.user.domain.vo.Token
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TokenAdapter : TokenCreatePort {

	companion object {
		private const val MAX_REFRESH_TOKEN_DATE = 14L
		private const val MAX_ACCESS_TOKEN_SECONDS = 60 * 30L
	}

	override fun generateRefreshToken(): Token {
		return Token(
			UUID.randomUUID().toString(),
			LocalDateTime.now().plusDays(MAX_REFRESH_TOKEN_DATE)
		)
	}

	override fun generateAccessToken(id: String): Token {
		return Token(
			id + UUID.randomUUID().toString(),
			LocalDateTime.now().plusSeconds(MAX_ACCESS_TOKEN_SECONDS)
		)
	}
}