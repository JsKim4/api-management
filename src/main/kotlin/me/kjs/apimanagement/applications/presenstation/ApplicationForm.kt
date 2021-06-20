package me.kjs.apimanagement.applications.presenstation

class ApplicationForm {
	class Create {
		data class Response(
			val title: String,
			val content: String,
			val clientId: String,
			val secretKey: String
		)

		data class Request(
			val title: String,
			val content: String
		)

	}

	class Find {
		class Response {
			data class One(
				val id: String,
				val title: String,
				val content: String,
				val clientId: String,
				val secretKey: String
			)

			data class Simple(
				val id: String,
				val title: String,
			)
		}

	}

}
