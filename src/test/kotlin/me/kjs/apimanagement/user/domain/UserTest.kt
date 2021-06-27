package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.domain.vo.Token
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

@DisplayName("유저 도메인 테스트")
class UserTest {
	companion object {
		const val name = "김준섭"
		const val email = "ggi4111@naver.com"
		const val password = "a123456!"
	}

	val passwordEncoder = object : PasswordEncoder {
		override fun match(encryptedPassword: String, lawPassword: String): Boolean {
			return encryptedPassword == lawPassword
		}

		override fun encrypt(lawPassword: String): String {
			return lawPassword
		}
	}

	val idGenerator = object : IdGeneratePort {
		override fun generateId(): String {
			return UUID.randomUUID().toString()
		}
	}

	@Test
	@DisplayName("유저 생성 테스트")
	fun createUser() {
		val user = User(idGenerator.generateId(),name, email, password,passwordEncoder)
		assertEquals(name, user.name)
		assertEquals(email, user.email)
		assertNotNull(user.id)
		assertTrue(user.isEqualPassword(password, passwordEncoder))
	}

	@Test
	@DisplayName("리프레시 토큰 발행 및 유효성 확인 테스트")
	fun tokenIssueAndCheckToken() {
		val user = User(idGenerator.generateId(),name, email, password,passwordEncoder)
		val clientId = "clientID0001"
		val refreshToken = UUID.randomUUID().toString()
		val expiredDateTime = LocalDateTime.now().plusDays(14)
		val token = Token(
			refreshToken,
			expiredDateTime
		)
		user.putAuthToken(clientId, token)
		assertTrue(user.validRefreshToken(clientId, refreshToken))
		assertFalse(user.validRefreshToken(clientId + "asd", refreshToken))
		assertFalse(user.validRefreshToken(clientId, refreshToken + "asd"))
		user.putAuthToken(clientId, Token(
			UUID.randomUUID().toString(),
			LocalDateTime.now().plusDays(14)
		))
		assertFalse(user.validRefreshToken(clientId, refreshToken))
	}

}