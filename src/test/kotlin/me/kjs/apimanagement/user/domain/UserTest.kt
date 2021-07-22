package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.domain.core.User
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

	private val passwordEncoder = object : PasswordEncoder {
		override fun match(encryptedPassword: String, lawPassword: String): Boolean {
			return encryptedPassword == lawPassword
		}

		override fun encrypt(lawPassword: String): String {
			return lawPassword
		}
	}

	private val idGenerator = object : IdGeneratePort {
		override fun generateId(): String {
			return UUID.randomUUID().toString()
		}
	}

	@Test
	@DisplayName("유저 생성 테스트")
	fun createUser() {
		val user = User.by(idGenerator.generateId(), name, email, password, passwordEncoder)
		assertEquals(name, user.name)
		assertEquals(email, user.email)
		assertNotNull(user.id)
		assertTrue(user.isEqualPassword(password, passwordEncoder))
	}

	@Test
	@DisplayName("리프레시 토큰 발행 및 유효성 확인 테스트")
	fun tokenIssueAndCheckToken() {
		val user = User.by(idGenerator.generateId(), name, email, password, passwordEncoder)
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
		user.putAuthToken(
			clientId, Token(
				UUID.randomUUID().toString(),
				LocalDateTime.now().plusDays(14)
			)
		)
		assertFalse(user.validRefreshToken(clientId, refreshToken))
	}

	@Test
	@DisplayName("유저 이름 변경 테스트")
	fun updateUserNameTest() {
		val user = User.by(idGenerator.generateId(), name, email, password, passwordEncoder)
		val updateName = "updateName"
		user.updateName(updateName)
		assertEquals(updateName, user.name)
	}

	@Test
	@DisplayName("유저 비밀번호 변경 테스트")
	fun updateUserPasswordTest() {
		val user = User.by(idGenerator.generateId(), name, email, password, passwordEncoder)
		val updatePassword = "updatePassword"
		user.updatePassword(updatePassword, passwordEncoder)
		assertTrue(user.isEqualPassword(updatePassword, passwordEncoder))
	}

	@Test
	@DisplayName("동일 비밀번호 체크 테스트")
	fun passwordCheckTest() {
		val user = User.by(idGenerator.generateId(), name, email, password, passwordEncoder)
		assertTrue(user.isEqualPassword(password, passwordEncoder))
		assertFalse(user.isEqualPassword("", passwordEncoder))
	}

	@Test
	@DisplayName("패스워드 체크 비동일 테스트")
	fun passwordCheckFailureTest() {
		val user = User.by(idGenerator.generateId(), name, email, UUID.randomUUID().toString(), passwordEncoder)
		assertFalse(user.isEqualPassword(UUID.randomUUID().toString(), passwordEncoder))
	}


}