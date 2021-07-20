package me.kjs.apimanagement.common


interface ResponseResult<T> {
	fun <R> listTo(f: (T) -> R): ResponseResult<R>
}

class Response {
	class Page<T>(
		val nowPage: Long,
		val contents: List<T>,
		val maxCount: Long
	) : ResponseResult<T> {
		override fun <R> listTo(f: (T) -> R): Page<R> {
			return Page(nowPage, contents.map { f(it) }, maxCount)
		}
	}

	class Slice<T>(
		val nowPage: Int,
		val contents: List<T>,
		val hasNext: Boolean
	) : ResponseResult<T> {
		override fun <R> listTo(f: (T) -> R): Slice<R> {
			return Slice(nowPage, contents.map { f(it) }, hasNext)
		}
	}
}

enum class ResponseType {
	SLICE, PAGE
}