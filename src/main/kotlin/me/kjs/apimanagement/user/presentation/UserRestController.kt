package me.kjs.apimanagement.user.presentation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserRestController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createUser(
		@RequestBody request: UserForm.Create.Request,
	): UserForm.Create.Response {
		TODO()
	}

	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	fun getUser(
		@PathVariable userId: String,
	): UserForm.Find.Response.One {
		TODO()
	}

	@PatchMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun updateUserPart(
		@PathVariable userId: String,
		@RequestBody request: UserForm.UpdatePart.Request,
	) {
		TODO()
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun withdrawUser(
		@PathVariable userId: String,
	) {
		TODO()
	}

}