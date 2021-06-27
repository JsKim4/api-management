package me.kjs.apimanagement.documentation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import me.kjs.apimanagement.DocumentationTestBase
import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.content
import me.kjs.apimanagement.user.adapter.out.presentation.UserAuthCreateRestController
import me.kjs.apimanagement.user.adapter.out.presentation.UserAuthPresentation
import me.kjs.apimanagement.user.adapter.out.presentation.UserAuthRestController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@DisplayName("유저 인증 관련 문서화")
class UserAuthDocumentation : DocumentationTestBase() {

	@MockBean
	lateinit var userAuthRestController: UserAuthRestController

	@MockBean
	lateinit var userAuthCreateRestController: UserAuthCreateRestController

	@Autowired
	lateinit var idGeneratePort: IdGeneratePort

	@Test
	@DisplayName("유저 인증 토큰 발급 문서화")
	fun userAuthCreateDocumentation() {
		val clientId = UUID.randomUUID().toString()
		val request = UserAuthPresentation.Create.Request(
			"testEmail@email.com",
			"a123456@!",
			clientId = clientId
		)

		val response = UserAuthPresentation.Token.Response(
			accessToken = UUID.randomUUID().toString(),
			accessTokenExpiredSecond = 12348603,
			refreshToken = UUID.randomUUID().toString(),
			refreshTokenExpiredSecond = 12348603
		)

		whenever(userAuthCreateRestController.createUserAuthInfo(any())).thenReturn(response)
		mockMvc.perform(
			RestDocumentationRequestBuilders.post("/users/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andExpect(status().isCreated)
			.andExpect(jsonPath("accessToken").exists())
			.andExpect(jsonPath("accessTokenExpiredSecond").exists())
			.andExpect(jsonPath("refreshToken").exists())
			.andExpect(jsonPath("refreshTokenExpiredSecond").exists())
			.andDo(
				document(
					"create-user-auth",
					requestFields(
						fieldWithPath("email").description("유저 이메일"),
						fieldWithPath("password").description("비밀번호"),
						fieldWithPath("clientId").description("클라이언트 ID")
					),
					responseFields(
						fieldWithPath("accessToken").description("엑세스 토큰"),
						fieldWithPath("refreshToken").description("리프레시 토큰"),
						fieldWithPath("accessTokenExpiredSecond").description("엑세스 토큰 남은 유효시간(초)"),
						fieldWithPath("refreshTokenExpiredSecond").description("리프레시 토큰 남은 유효시간(초)")
					)
				)
			)
	}

	@Test
	@DisplayName("토큰 재발급 문서화")
	fun refreshAccessTokenDocumentation() {

		val refreshToken = UUID.randomUUID().toString()
		val clientId = UUID.randomUUID().toString()
		val request = UserAuthPresentation.Refresh.Request(
			refreshToken = refreshToken,
			clientId = clientId
		)

		val response = UserAuthPresentation.Token.Response(
			accessToken = UUID.randomUUID().toString(),
			accessTokenExpiredSecond = 12348603,
			refreshToken = refreshToken,
			refreshTokenExpiredSecond = 12348603
		)

		val id = idGeneratePort.generateId()

		whenever(userAuthRestController.refreshAccessToken(any(), any())).thenReturn(response)

		mockMvc.perform(
			RestDocumentationRequestBuilders.post("/users/{userId}/auth/refresh", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andExpect(jsonPath("refreshToken").value(request.refreshToken))
			.andDo(
				document(
					"refresh-user-auth",
					pathParameters(
						parameterWithName("userId").description("유저 아이디")
					),
					requestFields(
						fieldWithPath("refreshToken").description("리프레시 토큰"),
						fieldWithPath("clientId").description("클라이언트 ID")
					),
					responseFields(
						fieldWithPath("accessToken").description("엑세스 토큰"),
						fieldWithPath("refreshToken").description("리프레시 토큰"),
						fieldWithPath("accessTokenExpiredSecond").description("엑세스 토큰 남은 유효시간(초)"),
						fieldWithPath("refreshTokenExpiredSecond").description("리프레시 토큰 남은 유효시간(초)")
					)
				)
			)
	}
}