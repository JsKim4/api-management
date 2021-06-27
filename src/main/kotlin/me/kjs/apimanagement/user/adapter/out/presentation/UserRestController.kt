package me.kjs.apimanagement.user.adapter.out.presentation

import me.kjs.apimanagement.user.application.port.`in`.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserRestController(
	private val createUserUseCase: CreateUserUseCase,
	private val getUserUseCase: GetUserUseCase,
	private val updatePasswordUseCase: UpdatePasswordUseCase,
	private val updateNameUseCase: UpdateNameUseCase,
	private val withdrawUserUseCase: WithdrawUserUseCase,
) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createUser(
		@RequestBody request: UserPresentation.Create.Request,
	): UserPresentation.Create.Response {
		val createdUser = createUserUseCase.createUser(
			UserForm.Create.Request(
				request.email,
				request.name,
				request.password
			)
		)
		return UserPresentation.Create.Response(
			createdUser.email,
			createdUser.name
		)
	}

	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	fun getUser(
		@PathVariable userId: String,
	): UserPresentation.Find.Response.One {
		val getUser = getUserUseCase.getUser(userId)
		return UserPresentation.Find.Response.One(
			getUser.email,
			getUser.name
		)
	}

	@PatchMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun updateUserPart(
		@PathVariable userId: String,
		@RequestBody request: UserPresentation.UpdatePart.Request,
	) {
		when (request.updateCommand) {
			UserPresentation.UpdatePart.Request.UpdateCommand.NAME -> updatePasswordUseCase.updatePassword(
				UserForm.Update.Request.Password(
					userId,
					request.value
				)
			)
			UserPresentation.UpdatePart.Request.UpdateCommand.PASSWORD -> updateNameUseCase.updateName(
				UserForm.Update.Request.Name(
					userId,
					request.value
				)
			)
		}
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun withdrawUser(
		@PathVariable userId: String,
	) {
		withdrawUserUseCase.withdrawUser(userId)
	}

}