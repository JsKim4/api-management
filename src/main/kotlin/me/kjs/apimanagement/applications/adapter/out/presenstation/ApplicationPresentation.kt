package me.kjs.apimanagement.applications.adapter.out.presenstation

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationForm

interface ApplicationPresentation {
	interface Create {
		data class Response(
			val title: String,
			val content: String,
			val clientId: String,
			val secretKey: String
		)

		data class Request(
			val title: String,
			val content: String
		) {
			fun toForm() = ApplicationForm.Create.Request(title, content)
		}

	}

	interface Find {
		interface Response {
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
