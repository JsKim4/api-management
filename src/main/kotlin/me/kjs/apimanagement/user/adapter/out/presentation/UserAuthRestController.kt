package me.kjs.apimanagement.user.adapter.out.presentation

import me.kjs.apimanagement.user.application.port.`in`.CreateTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.RefreshTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserAuthForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/auth")
class UserAuthRestController(
	private val refreshTokenUseCase: RefreshTokenUseCase,
) {

	@PostMapping("/refresh")
	@ResponseStatus(HttpStatus.CREATED)
	fun refreshAccessToken(
		@RequestBody request: UserAuthPresentation.Refresh.Request,
		@PathVariable userId: String
	): UserAuthPresentation.Token.Response {
		val tokenResponse = refreshTokenUseCase.refreshToken(
			UserAuthForm.Refresh.Request(
				userId,
				request.clientId,
				request.refreshToken
			)
		)
		return with(tokenResponse) {
			UserAuthPresentation.Token.Response(
				accessToken, refreshToken, accessTokenExpiredSecond, refreshTokenExpiredSecond
			)
		}
	}

}