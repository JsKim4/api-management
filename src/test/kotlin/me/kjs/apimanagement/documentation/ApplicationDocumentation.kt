package me.kjs.apimanagement.documentation

import com.nhaarman.mockitokotlin2.whenever
import me.kjs.apimanagement.DocumentationTestBase
import me.kjs.apimanagement.applications.presenstation.ApplicationForm
import me.kjs.apimanagement.applications.presenstation.ApplicationRestController
import me.kjs.apimanagement.content
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("어플리케이션 문서화")
class ApplicationDocumentation : DocumentationTestBase() {

	@MockBean
	lateinit var applicationRestController: ApplicationRestController

	@Test
	@DisplayName("어플리케이션 생성 문서화")
	fun createApplicationDocumentation() {
		val title = "application title"
		val content = "application content"
		val clientId = "application-client-id"
		val secretKey = "application-secret-key"
		val request = ApplicationForm.Create.Request(
			title = title,
			content = content
		)
		val response = ApplicationForm.Create.Response(
			title = title,
			content = content,
			clientId = clientId,
			secretKey = secretKey
		)

		whenever(applicationRestController.createApplication(request)).thenReturn(response)

		mockMvc.perform(
			RestDocumentationRequestBuilders.post("/applications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andExpect(status().isCreated)
			.andDo(
				document(
					"create-application",
					requestFields(
						fieldWithPath("title").description("application 이름"),
						fieldWithPath("content").description("application 내용"),
					),
					responseFields(
						fieldWithPath("title").description("application 이름"),
						fieldWithPath("content").description("application 내용"),
						fieldWithPath("clientId").description("application client id"),
						fieldWithPath("secretKey").description("application secret key"),
					)
				)
			)
	}

	@Test
	@DisplayName("어플리케이션 조회 문서화")
	fun getApplicationTest() {

	}

}