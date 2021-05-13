package me.kjs.apimanagement.user.presentation

class UserForm {
	class Create {
		class Request {

		}

		class Response {

		}
	}

	class FindOne {
		class Response {

		}

	}

	class UpdatePart {
		class Request {

			enum class UpdateCommand {
				PASSWORD,
				NAME,
			}
		}
	}
}