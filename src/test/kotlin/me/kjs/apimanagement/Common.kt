package me.kjs.apimanagement

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import kotlin.reflect.KClass

internal class Common


fun <T> MockHttpServletRequestBuilder.content(request: T): RequestBuilder {
	return this.content(jacksonObjectMapper().writeValueAsString(request))
}

object InMemoryDb {
	val map = HashMap<String, HashMap<String, Any>>()

	fun save(key: String, value: Any) {
		val hashMap = map[value::class.simpleName]
		if (hashMap == null) {
			map[value::class.simpleName!!] = HashMap()
		}
		map[value::class.simpleName]!![key] = value
	}

	fun find(key: String, classes: KClass<Any>): Any? {
		val hashMap = map[classes.simpleName]
		if (hashMap == null) {
			map[classes.simpleName!!] = HashMap()
			return null
		}
		return  map[classes.simpleName]!![key]
	}
}