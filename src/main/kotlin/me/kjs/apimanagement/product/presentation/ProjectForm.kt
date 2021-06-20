package me.kjs.apimanagement.product.presentation

class ProjectForm {
	class Put {
		data class Request(
			val title: String,
			val content: String
		)

		data class Response(
			val code: ProductCode,
			val title: String,
			val content: String
		)

	}

	class Find {
		class Response {
			data class All(
				val contents: List<One>
			)

			data class One(
				val code: ProductCode,
				val title: String,
				val content: String
			)

		}

	}

}
