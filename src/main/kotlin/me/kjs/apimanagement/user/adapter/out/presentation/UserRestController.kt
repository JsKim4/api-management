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
		return createUserUseCase.createUser(request.toForm()).toPresentation()
	}

	fun UserPresentation.Create.Request.toForm() = UserForm.Create.Request(email, name, password)
	fun UserForm.Create.Response.toPresentation() = UserPresentation.Create.Response(email, name)


	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	fun getUser(
		@PathVariable userId: String,
	): UserPresentation.Find.Response.One {
		return getUserUseCase.getUser(userId).toPresentation()
	}

	fun UserForm.Find.Response.One.toPresentation() = UserPresentation.Find.Response.One(email, name)

	@PatchMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun updateUserPart(
		@PathVariable userId: String,
		@RequestBody request: UserPresentation.UpdatePart.Request,
	) {
		when (request.updateCommand) {
			UserPresentation.UpdatePart.Request.UpdateCommand.NAME -> updatePasswordUseCase.updatePassword(
				request.toPasswordFormWith(userId)
			)
			UserPresentation.UpdatePart.Request.UpdateCommand.PASSWORD -> updateNameUseCase.updateName(
				request.toNameFormWith(userId)
			)
		}
	}

	fun UserPresentation.UpdatePart.Request.toPasswordFormWith(userId: String) =
		UserForm.Update.Request.Password(userId, value)

	fun UserPresentation.UpdatePart.Request.toNameFormWith(userId: String) =
		UserForm.Update.Request.Name(userId, value)

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun withdrawUser(
		@PathVariable userId: String,
	) {
		withdrawUserUseCase.withdrawUser(userId)
	}

}