package me.kjs.apimanagement.user.adapter.out.presentation

import me.kjs.apimanagement.user.application.port.`in`.CreateTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.DeleteTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.RefreshTokenUseCase
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
		return createTokenUseCase.createToken(request.toForm()).toPresentation()
	}

	private fun UserAuthForm.Token.Response.toPresentation() = UserAuthPresentation.Token.Response(
		accessToken, refreshToken, accessTokenExpiredSecond, refreshTokenExpiredSecond
	)
}