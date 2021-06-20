package me.kjs.apimanagement

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@SpringBootTest
@AutoConfigureRestDocs
@Import(RestDocsConfig::class)
@ExtendWith(RestDocumentationExtension::class)
class DocumentationTestBase {

	lateinit var mockMvc: MockMvc

	val objectMapper = ObjectMapper()

	@Autowired
	lateinit var ctx: WebApplicationContext

	@BeforeEach
	fun setup(webApplicationContext: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {

		mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
			.addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true)) // 필터 추가
			.alwaysDo<DefaultMockMvcBuilder>(
				MockMvcResultHandlers.print()
			).apply<DefaultMockMvcBuilder>(
				documentationConfiguration(restDocumentation)
					.operationPreprocessors()
					.withResponseDefaults(prettyPrint())
			)
			.build()
	}
}