package me.kjs.apimanagement

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.operation.preprocess.Preprocessors

@TestConfiguration
class RestDocsConfig {
	@Bean
	fun restDocsMockMvcConfigurationCustomizer(): RestDocsMockMvcConfigurationCustomizer {
		return RestDocsMockMvcConfigurationCustomizer {
			it.operationPreprocessors()
				.withResponseDefaults(Preprocessors.prettyPrint())
				.withRequestDefaults(Preprocessors.prettyPrint())
		}
	}
}