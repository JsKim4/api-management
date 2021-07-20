package me.kjs.apimanagement.applications.application.port.`in`

interface ApplicationForm {
	interface Find {
		interface Response {
			data class Simple(
				val id: String,
				val title: String,
			)

			data class One(
				val id: String,
				val title: String,
				val content: String,
				val clientId: String,
				val secretKey: String
			)
		}
	}

	interface Create {
		data class Request(
			val title: String,
			val content: String
		)

		data class Response(
			val title: String,
			val content: String,
			val clientId: String,
			val secretKey: String
		)
	}
}
