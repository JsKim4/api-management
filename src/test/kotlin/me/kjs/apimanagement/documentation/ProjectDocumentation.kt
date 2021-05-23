package me.kjs.apimanagement.documentation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import me.kjs.apimanagement.DocumentationTestBase
import me.kjs.apimanagement.content
import me.kjs.apimanagement.product.presentation.ProjectCode
import me.kjs.apimanagement.product.presentation.ProjectForm
import me.kjs.apimanagement.product.presentation.ProjectRestController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("프로젝트 관련 문서화")
class ProjectDocumentation : DocumentationTestBase() {

	@MockBean
	private lateinit var mockProjectRestController: ProjectRestController

	@Test
	@DisplayName("프로젝트 생성 문서화")
	fun projectCreateDocumentation() {
		val title = "Project Title"
		val content = "연습용 콘텐츠 입니다."
		val request = ProjectForm.Put.Request(title, content)
		whenever(mockProjectRestController.createProject(request, ProjectCode.LOTTO)).thenReturn(
			ResponseEntity.noContent().build()
		)

		mockMvc.perform(
			RestDocumentationRequestBuilders.put("/projects/{projectCode}", ProjectCode.LOTTO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andDo(
				document(
					"put-project",
					pathParameters(
						parameterWithName("projectCode").description("프로젝트 코드")
					),
					requestFields(
						fieldWithPath("title").description("프로젝트 타이틀"),
						fieldWithPath("content").description("프로젝트 내용")
					)
				)
			)
	}

	@Test
	@DisplayName("프로젝트 단일 조회")
	fun projectQueryDocumentation() {
		val title = "Project Title"
		val content = "연습용 콘텐츠 입니다."
		whenever(mockProjectRestController.queryProject(any())).thenReturn(
			ProjectForm.Find.Response.One(
				code = ProjectCode.LOTTO,
				title = title,
				content = content
			)
		)
		mockMvc.perform(RestDocumentationRequestBuilders.get("/projects/{projectCode}", ProjectCode.LOTTO))
			.andExpect(status().isOk)
			.andDo(
				document(
					"query-project",
					pathParameters(
						parameterWithName("projectCode").description("프로젝트 코드")
					),
					responseFields(
						fieldWithPath("title").description("프로젝트 타이틀"),
						fieldWithPath("content").description("프로젝트 내용"),
						fieldWithPath("code").description("프로젝트 코드")
					)
				)
			)
	}

	@Test
	@DisplayName("프로젝트 전체 조회")
	fun projectsQueryDocumentation() {
		val title = "Project Title"
		val content = "연습용 콘텐츠 입니다."
		whenever(mockProjectRestController.queryProjects()).thenReturn(
			ProjectForm.Find.Response.All(
				contents = listOf(
					ProjectForm.Find.Response.One(
						code = ProjectCode.LOTTO,
						title = title,
						content = content
					)
				)
			)
		)
		mockMvc.perform(RestDocumentationRequestBuilders.get("/projects"))
			.andExpect(status().isOk)
			.andDo(
				document(
					"query-projects",
					responseFields(
						fieldWithPath("contents[].title").description("프로젝트 타이틀"),
						fieldWithPath("contents[].content").description("프로젝트 내용"),
						fieldWithPath("contents[].code").description("프로젝트 코드")
					)
				)
			)
	}


}