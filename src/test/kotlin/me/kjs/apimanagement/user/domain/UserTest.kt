package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.common.PasswordEncoder
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

	@Test
	@DisplayName("유저 생성 테스트")
	fun createUser() {
		val passwordEncoder = object : PasswordEncoder {}
		val user = User(name, email, passwordEncoder.encrypt(password))
		assertEquals(name, user.name)
		assertEquals(email, user.email)
		assertNotNull(user.id)
		assertTrue(user.isEqualPassword(password, passwordEncoder))
	}

	@Test
	@DisplayName("리프레시 토큰 발행 및 유효성 확인 테스트")
	fun tokenIssueAndCheckToken() {
		val passwordEncoder = object : PasswordEncoder {}
		val user = User(name, email, passwordEncoder.encrypt(password))
		val clientId = "clientID0001"
		val refreshToken = UUID.randomUUID().toString()
		val expiredDateTime = LocalDateTime.now().plusDays(14)
		user.putAuthToken(clientId, refreshToken, expiredDateTime)
		assertTrue(user.validRefreshToken(clientId, refreshToken))
		assertFalse(user.validRefreshToken(clientId + "asd", refreshToken))
		assertFalse(user.validRefreshToken(clientId, refreshToken + "asd"))
		user.putAuthToken(clientId, UUID.randomUUID().toString(), expiredDateTime)
		assertFalse(user.validRefreshToken(clientId, refreshToken))
	}

}