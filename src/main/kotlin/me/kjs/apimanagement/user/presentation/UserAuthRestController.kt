package me.kjs.apimanagement.user.presentation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/auth")
class UserAuthRestController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createUserAuthInfo(
		@RequestBody request: UserAuthForm.Create.Request,
		@PathVariable userId: String
	): UserAuthForm.Token.Response {
		TODO()
	}

	@PostMapping("/refresh")
	@ResponseStatus(HttpStatus.CREATED)
	fun refreshAccessToken(
		@RequestBody request: UserAuthForm.Refresh.Request,
		@PathVariable userId: String
	): UserAuthForm.Token.Response {
		TODO()
	}

}