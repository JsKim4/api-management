package me.kjs.apimanagement

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

internal class Common



fun <T> MockHttpServletRequestBuilder.content(request: T): RequestBuilder {
	return this.content(jacksonObjectMapper().writeValueAsString(request))
}