package me.kjs.apimanagement.user.domain.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UserEmailTest {

	@Test
	@DisplayName("유저 이메일 생성 테스트")
	fun emailCreateTest() {
		val legalEmail = "ggi4111@naver.com"
		val email1 = UserEmail(legalEmail)
		assertEquals(email1.email, legalEmail)
		val illegalEmail = "ggi4111"
		assertThrows<RuntimeException> {
			UserEmail(illegalEmail)
		}
	}
}