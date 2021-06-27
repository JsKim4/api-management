package me.kjs.apimanagement.documentation

import com.nhaarman.mockitokotlin2.whenever
import me.kjs.apimanagement.DocumentationTestBase
import me.kjs.apimanagement.applications.presenstation.ApplicationForm
import me.kjs.apimanagement.applications.presenstation.ApplicationRestController
import me.kjs.apimanagement.common.Response
import me.kjs.apimanagement.common.ResponseType
import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.content
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("어플리케이션 문서화")
class ApplicationDocumentation : DocumentationTestBase() {

	@MockBean
	lateinit var applicationRestController: ApplicationRestController

	@Autowired
	lateinit var idGeneratePort: IdGeneratePort

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
	@DisplayName("어플리케이션 리스트 조회 문서화")
	fun getApplicationsDocumentation() {

		val page = 5
		val contentCount = 100
		val list = mutableListOf<ApplicationForm.Find.Response.Simple>()

		for (i in 1..contentCount) {
			list.add(ApplicationForm.Find.Response.Simple(idGeneratePort.generateId(), "Title $i"))
		}

		val slice: Response.Slice<ApplicationForm.Find.Response.Simple> = Response.Slice(
			page,
			list,
			true
		)

		whenever(applicationRestController.getApplications(ResponseType.SLICE, page, contentCount)).thenReturn(slice)

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/applications")
				.contentType(MediaType.APPLICATION_JSON)
				.header("ResponseType", "SLICE")
				.param("contentCount", "$contentCount")
				.param("page", "$page")
		)
			.andExpect(status().isOk)
			.andDo(
				document(
					"query-applications",
					requestHeaders(
						headerWithName("ResponseType").description("SLICE")
					),
					requestParameters(
						parameterWithName("page").description("페이지 정보 1 부터 시작"),
						parameterWithName("contentCount").description("페이지당 콘텐츠 개수 1 ~ 100")
					),
					responseFields(
						fieldWithPath("nowPage").description("페이지 정보 1 부터 시작"),
						fieldWithPath("hasNext").description("다음 페이지 존재 여부"),
						fieldWithPath("contents").description("콘텐츠 리스트"),
						fieldWithPath("contents[].id").description("콘텐츠 리스트"),
						fieldWithPath("contents[].title").description("콘텐츠 제목")
					)
				)
			)
	}

	@Test
	@DisplayName("어플리케이션 단건 조회 문서화")
	fun getApplicationDocumentation() {
		val id = idGeneratePort.generateId()
		val title = "application title"
		val content = "application content"
		val clientId = "application-client-id"
		val secretKey = "application-secret-key"

		val response = ApplicationForm.Find.Response.One(
			id = id,
			title = title,
			content = content,
			clientId = clientId,
			secretKey = secretKey
		)


		whenever(applicationRestController.getApplication(id)).thenReturn(response)

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/applications/{applicationId}", id)
		)
			.andExpect(status().isOk)
			.andDo(
				document(
					"query-application",
					pathParameters(
						parameterWithName("applicationId").description("에플리케이션 id")
					),
					responseFields(
						fieldWithPath("id").description("application 고유번호"),
						fieldWithPath("title").description("application 이름"),
						fieldWithPath("content").description("application 내용"),
						fieldWithPath("clientId").description("application client id"),
						fieldWithPath("secretKey").description("application secret key"),
					)
				)
			)
	}

	@Test
	@DisplayName("어플리케이션 삭제 문서화")
	fun deleteApplicationDocumentation() {
		val id = idGeneratePort.generateId()
		mockMvc.perform(
			RestDocumentationRequestBuilders.delete("/applications/{applicationId}", id)
		)
			.andExpect(status().isNoContent)
			.andDo(
				document(
					"delete-application",
					pathParameters(
						parameterWithName("applicationId").description("에플리케이션 id")
					)
				)
			)
	}

	@Test
	@DisplayName("어플리케이션 시크릿키 변경 문서화")
	fun updateSecretKeyApplicationDocumentation() {
		val id = idGeneratePort.generateId()
		mockMvc.perform(
			RestDocumentationRequestBuilders.patch("/applications/{applicationId}/secret-key", id)
		)
			.andExpect(status().isNoContent)
			.andDo(
				document(
					"update-application-secret-key",
					pathParameters(
						parameterWithName("applicationId").description("에플리케이션 id")
					)
				)
			)
	}


}