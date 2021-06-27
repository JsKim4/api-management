package me.kjs.apimanagement.documentation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.whenever
import me.kjs.apimanagement.DocumentationTestBase
import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.content
import me.kjs.apimanagement.user.adapter.out.presentation.UserPresentation
import me.kjs.apimanagement.user.adapter.out.presentation.UserRestController
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

@DisplayName("유저 문서화")
class UserDocumentation : DocumentationTestBase() {

	@MockBean
	lateinit var userRestController: UserRestController

	@Autowired
	lateinit var idGeneratePort: IdGeneratePort

	@Test
	@DisplayName("유저 생성 문서화")
	fun createUserDocumentation() {
		val request = UserPresentation.Create.Request(
			email = "testemail@mail.com",
			name = "테스터",
			password = "password123@",
		)
		val response = UserPresentation.Create.Response(
			email = "testemail@mail.com",
			name = "테스터",
		)
		whenever(userRestController.createUser(any())).thenReturn(response)

		mockMvc.perform(
			RestDocumentationRequestBuilders
				.post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andExpect(status().isCreated)
			.andExpect(jsonPath("email").value(request.email))
			.andExpect(jsonPath("name").value(request.name))
			.andDo(
				document(
					"create-user",
					requestFields(
						fieldWithPath("email").description("이메일"),
						fieldWithPath("name").description("이름"),
						fieldWithPath("password").description("비밀번호"),
					),
					responseFields(
						fieldWithPath("email").description(""),
						fieldWithPath("name").description("이름"),
					)
				)
			)
	}

	@Test
	@DisplayName("유저 조회 문서화")
	fun getUserDocumentation() {
		val response = UserPresentation.Find.Response.One(
			email = "testemail@mail.com",
			name = "테스터",
		)
		whenever(userRestController.getUser(any())).thenReturn(response)
		val id = idGeneratePort.generateId()
		mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{userId}", id))
			.andExpect(status().isOk)
			.andDo(
				document(
					"get-user",
					pathParameters(
						parameterWithName("userId").description("유저 아이디")
					),
					responseFields(
						fieldWithPath("email").description("이메일"),
						fieldWithPath("name").description("이름")
					)
				)
			)
	}

	@Test
	@DisplayName("유저 업데이트 문서화")
	fun updateUserPartDocumentation() {
		val request = UserPresentation.UpdatePart.Request(
			UserPresentation.UpdatePart.Request.UpdateCommand.NAME,
			"value"
		)
		val id = idGeneratePort.generateId()
		doNothing().whenever(userRestController).updateUserPart(any(), any())
		mockMvc.perform(
			RestDocumentationRequestBuilders
				.patch("/users/{userId}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andExpect(status().isNoContent)
			.andDo(
				document(
					"update-user-part",
					pathParameters(
						parameterWithName("userId").description("유저 아이디")
					),
					requestFields(
						fieldWithPath("updateCommand").description("update Command"),
						fieldWithPath("value").description("변경할 value")
					)
				)
			)

	}

	@Test
	@DisplayName("유저 삭제 문서화")
	fun deleteUserDocumentation() {
		doNothing().whenever(userRestController).withdrawUser(any())
		val id = idGeneratePort.generateId()
		mockMvc.perform(RestDocumentationRequestBuilders.delete("/users/{userId}", id))
			.andExpect(status().isNoContent)
			.andDo(
				document(
					"delete-user",
					pathParameters(
						parameterWithName("userId").description("유저 아이디")
					)
				)
			)
	}

}

