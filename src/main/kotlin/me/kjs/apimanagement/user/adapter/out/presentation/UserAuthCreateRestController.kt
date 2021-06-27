package me.kjs.apimanagement.user.adapter.out.presentation

import me.kjs.apimanagement.user.application.port.`in`.CreateTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserAuthForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/auth")
class UserAuthCreateRestController(
	private val createTokenUseCase: CreateTokenUseCase,
) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createUserAuthInfo(
		@RequestBody request: UserAuthPresentation.Create.Request,
	): UserAuthPresentation.Token.Response {
		val tokenResponse = createTokenUseCase.createToken(
			UserAuthForm.Create.Request(
				request.email,
				request.password,
				request.clientId
			)
		)
		return with(tokenResponse) {
			UserAuthPresentation.Token.Response(
				accessToken, refreshToken, accessTokenExpiredSecond, refreshTokenExpiredSecond
			)
		}
	}

}