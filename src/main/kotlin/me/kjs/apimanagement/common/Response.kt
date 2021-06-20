package me.kjs.apimanagement.common


interface ResponseResult<T>

class Response {
	class Page<T>(
		val nowPage: Long,
		val contents: List<T>,
		val maxCount: Long
	) : ResponseResult<T>

	class Slice<T>(
		val nowPage: Int,
		val contents: List<T>,
		val hasNext: Boolean
	) : ResponseResult<T>
}

enum class ResponseType {
	SLICE, PAGE
}