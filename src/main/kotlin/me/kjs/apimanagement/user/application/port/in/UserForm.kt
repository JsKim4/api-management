package me.kjs.apimanagement.user.application.port.`in`

interface UserForm {
	interface Create {
		data class Request(
			val email: String,
			val name: String,
			val password: String,
		)

		data class Response(
			val email: String,
			val name: String,
		)
	}

	interface Find {
		interface Response {
			data class One(
				val email: String,
				val name: String,
			)
		}
	}

	interface Update {
		interface Request {
			data class Password(
				val userId: String,
				val password: String
			)

			data class Name(
				val userId: String,
				val name: String
			)
		}
	}
}