package me.kjs.apimanagement.documentation

import com.nhaarman.mockitokotlin2.whenever
import me.kjs.apimanagement.DocumentationTestBase
import me.kjs.apimanagement.applications.adapter.out.presenstation.ApplicationProductPresentation
import me.kjs.apimanagement.applications.adapter.out.presenstation.ApplicationProductRestController
import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.content
import me.kjs.apimanagement.product.adapter.out.presentation.ProductCode
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@DisplayName("어플리케이션 / 프로젝트 문서화")
class ApplicationProductDocumentation : DocumentationTestBase() {

	@MockBean
	lateinit var applicationProductRestController: ApplicationProductRestController

	@Autowired
	lateinit var idGeneratePort: IdGeneratePort

	@Test
	@DisplayName("어플리케이션 프로젝트 사용 신청")
	fun createApplicationProductDocumentation() {
		val applicationId = idGeneratePort.generateId()
		val productCode: ProductCode = ProductCode.LOTTO
		val request = ApplicationProductPresentation.Create.Request(
			"cause"
		)
		val response = ApplicationProductPresentation.Create.Response(
			applicationId,
			productCode,
			ApplicationProductPresentation.ProcessStatus.REQUESTED
		)
		whenever(applicationProductRestController.createApplicationProduct(applicationId, productCode, request)).thenReturn(
			response
		)

		mockMvc.perform(
			RestDocumentationRequestBuilders.post(
				"/applications/{applicationId}/products/{productCode}",
				applicationId,
				productCode
			)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
		)
			.andExpect(status().isCreated)
			.andDo(
				document(
					"create-application-product",
					requestFields(
						fieldWithPath("cause").description("신청 사유")
					),
					responseFields(
						fieldWithPath("applicationId").description("애플리케이션 id"),
						fieldWithPath("productCode").description("신청한 상품 코드"),
						fieldWithPath("processStatus").description("진행 상태")
					)
				)
			)
	}

	@Test
	@DisplayName("어플리케이션 프로젝트 사용 중지")
	fun deleteApplicationProductDocumentation() {
		val applicationId = idGeneratePort.generateId()
		val productCode = ProductCode.LOTTO
		mockMvc.perform(
			RestDocumentationRequestBuilders.delete(
				"/applications/{applicationId}/products/{productCode}",
				applicationId,
				productCode
			)
		)
			.andExpect(status().isNoContent)
			.andDo(
				document(
					"delete-application-product",
					pathParameters(
						parameterWithName("applicationId").description("애플리케이션 id"),
						parameterWithName("productCode").description("프로덕트 code"),
					)
				)
			)
	}

}