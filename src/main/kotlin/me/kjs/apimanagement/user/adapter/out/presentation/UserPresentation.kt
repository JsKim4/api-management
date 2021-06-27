package me.kjs.apimanagement.user.adapter.out.presentation

interface UserPresentation {
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

	interface UpdatePart {
		class Request(
			val updateCommand: UpdateCommand,
			val value: String,
		) {

			enum class UpdateCommand {
				PASSWORD,
				NAME,
			}
		}
	}
}