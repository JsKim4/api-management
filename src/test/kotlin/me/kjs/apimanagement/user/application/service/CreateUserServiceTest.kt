package me.kjs.apimanagement.user.application.service

import me.kjs.apimanagement.common.MapDatabase
import me.kjs.apimanagement.common.TestUserAdapter
import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.user.application.port.`in`.CreateUserUseCase
import me.kjs.apimanagement.user.application.port.`in`.GetUserUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserForm
import me.kjs.apimanagement.user.application.port.out.FindEmailPort
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.application.port.out.RecordUserPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

@DisplayName("유저 생성 서비스 테스트")
class CreateUserServiceTest {

	private val passwordEncoder = object : PasswordEncoder {
		override fun match(encryptedPassword: String, lawPassword: String): Boolean {
			return encryptedPassword == lawPassword
		}

		override fun encrypt(lawPassword: String): String {
			return lawPassword
		}
	}

	private val findEmailPort: FindEmailPort = TestUserAdapter()
	private val findUserPort: FindUserPort = TestUserAdapter()
	private val recordUserPort: RecordUserPort = TestUserAdapter()

	private val idGeneratePort = object : IdGeneratePort {
		override fun generateId(): String {
			return UUID.randomUUID().toString()
		}
	}


	private val createUserUseCase: CreateUserUseCase = CreateUserService(
		recordUserPort,
		findEmailPort,
		passwordEncoder,
		idGeneratePort
	)

	private val getUserUseCase: GetUserUseCase = GetUserService(
		findUserPort
	)

	@BeforeEach
	fun setUp() {
		MapDatabase.clear()
	}

	@Test
	@DisplayName("유저 생성 테스트")
	fun createUserTest() {
		val email = "email@email.com"
		val name = "userName"
		val password = "password@!"
		val request = UserForm.Create.Request(email, name, password)
		val createUser = createUserUseCase.createUser(request)
		assertEquals(request.email, createUser.email)
		assertEquals(request.name, createUser.name)
		assertNotNull(createUser.userId)
	}

	@Test
	@DisplayName("유저 생성 이메일 중복으로 실패하는 테스트")
	fun createUserFailTest() {
		val email = "email@email.com"
		val name = "userName"
		val password = "password@!"
		val request = UserForm.Create.Request(email, name, password)
		createUserUseCase.createUser(request)

		val assertThrows = assertThrows<Exception> {
			createUserUseCase.createUser(request)
		}
	}

	@Test
	@DisplayName("유저 조회 테스트")
	fun getUserTest() {
		val email = "email@email.com"
		val name = "userName"
		val password = "password@!"
		val request = UserForm.Create.Request(email, name, password)
		val createUser = createUserUseCase.createUser(request)

		val findUser = getUserUseCase.getUser(createUser.userId)
		with(findUser) {
			assertEquals(createUser.userId, userId)
			assertEquals(email, this.email)
			assertEquals(name, this.name)
		}
	}

	@Test
	@DisplayName("존재하지 않는 유저 조회 테스트")
	fun getUserNotExistTest() {
		assertThrows<Exception> {
			getUserUseCase.getUser(idGeneratePort.generateId())
		}

	}


}