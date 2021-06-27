package me.kjs.apimanagement.user.application.service

import me.kjs.apimanagement.user.application.port.`in`.CreateTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.RefreshTokenUseCase
import me.kjs.apimanagement.user.application.port.`in`.UserAuthForm
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.application.port.out.TokenCreatePort
import org.springframework.stereotype.Service

@Service
class PutUserAuthService(
	private val findUserPort: FindUserPort,
	private val passwordEncoder: PasswordEncoder,
	private val tokenCreatePort: TokenCreatePort
) : CreateTokenUseCase, RefreshTokenUseCase {

	override fun createToken(createRequest: UserAuthForm.Create.Request): UserAuthForm.Token.Response {
		val email = createRequest.email
		val password = createRequest.password
		val clientId = createRequest.clientId
		val findUser = findUserPort.findUserByEmail(email) ?: TODO()
		if (!findUser.isEqualPassword(password, passwordEncoder)) {
			TODO()
		}
		val refreshToken = tokenCreatePort.generateRefreshToken()
		val accessToken = tokenCreatePort.generateAccessToken(findUser.id)
		findUser.putAuthToken(clientId, refreshToken)
		return UserAuthForm.Token.Response(
			accessToken.value,
			refreshToken.value,
			accessToken.expiredSeconds,
			refreshToken.expiredSeconds
		)
	}

	override fun refreshToken(refreshRequest: UserAuthForm.Refresh.Request): UserAuthForm.Token.Response {
		val refreshTokenValue = refreshRequest.refreshToken
		val clientId = refreshRequest.clientId
		val userId = refreshRequest.userId
		val findUser = findUserPort.findUser(userId) ?: TODO()
		if (!findUser.validRefreshToken(clientId, refreshTokenValue)) {
			TODO()
		}
		val accessToken = tokenCreatePort.generateAccessToken(userId)
		val refreshToken = findUser.findAuthTokenByClientId(clientId) ?: TODO()
		return UserAuthForm.Token.Response(
			accessToken.value,
			refreshToken.value,
			accessToken.expiredSeconds,
			refreshToken.expiredSeconds,
		)
	}
}