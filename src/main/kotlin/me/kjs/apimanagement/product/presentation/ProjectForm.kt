package me.kjs.apimanagement.product.presentation

class ProjectForm {
	class Put {
		data class Request(
			val title: String,
			val content: String
		)

		data class Response(
			val code: ProjectCode,
			val title: String,
			val content: String
		)

	}

	class Find {
		class Response {
			data class All(
				val contents: kotlin.collections.List<One>
			)

			data class One(
				val code: ProjectCode,
				val title: String,
				val content: String
			)

		}

	}

}
