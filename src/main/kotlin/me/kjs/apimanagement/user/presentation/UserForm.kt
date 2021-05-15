package me.kjs.apimanagement.user.presentation

import com.sun.istack.NotNull

interface UserForm {
	interface Create {
		data class Request(
			@NotNull
			val email: String,
			@NotNull
			val name: String,
			@NotNull
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