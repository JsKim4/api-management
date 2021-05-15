package me.kjs.apimanagement.user.domain.vo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("유저 이름 테스트")
class UserNameTest {

	companion object {
		const val name = "김준섭"
		const val shortName = "일"
		const val longName = "일이삼사오육칠팔구십일이삼사오육칠팔구십"
		const val veryLongName = "일이삼사오육칠팔구십일이삼사오육칠팔구십일"
	}

	@Test
	@DisplayName("유저 이름 생성 테스트")
	fun nameCreateTest() {
		val userName = UserName(name)
		assertEquals(userName.name, name)
		val longUserName = UserName(longName)
		assertEquals(longUserName.name, longName)
		assertThrows<RuntimeException> {
			UserName(veryLongName)
		}
		assertThrows<RuntimeException> {
			UserName(shortName)
		}
	}
}