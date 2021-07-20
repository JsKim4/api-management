package me.kjs.apimanagement.user.adapter.out.presentation

import me.kjs.apimanagement.user.application.port.`in`.DeleteTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.RefreshTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserAuthForm
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/auth")
class UserAuthRestController(
	private val refreshTokenUseCase: RefreshTokenUseCase,
	private val deleteTokenUseCase: DeleteTokenUseCase,
) {

	@PostMapping("/refresh")
	@ResponseStatus(HttpStatus.CREATED)
	fun refreshAccessToken(
		@RequestBody request: UserAuthPresentation.Refresh.Request,
		@PathVariable userId: String
	): UserAuthPresentation.Token.Response {
		val response = refreshTokenUseCase.refreshToken(
			request.toFormWith(userId)
		)
		return response.toPresentation()
	}

	private fun UserAuthForm.Token.Response.toPresentation() = UserAuthPresentation.Token.Response(
		accessToken, refreshToken, accessTokenExpiredSecond, refreshTokenExpiredSecond
	)

	@DeleteMapping("/client/{clientId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteUserAuthInfo(
		@PathVariable clientId: String,
		@PathVariable userId: String
	) {
		deleteTokenUseCase.deleteToken(userId, clientId)
	}

}