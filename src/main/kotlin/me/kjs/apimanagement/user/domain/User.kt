package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.domain.vo.Token
import me.kjs.apimanagement.user.domain.vo.UserEmail
import me.kjs.apimanagement.user.domain.vo.UserName

class User(
	val id: String,
	name: String,
	email: String,
	password: String,
	passwordEncoder: PasswordEncoder
) {
	private var userName: UserName = UserName(name)
	private var userEmail: UserEmail = UserEmail(email)
	private var password: String = passwordEncoder.encrypt(password)
	private var authList: List<UserAuth> = ArrayList()

	val name: String
		get() = userName.name
	val email: String
		get() = userEmail.email

	fun isEqualPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
		return passwordEncoder.match(this.password, password)
	}

	fun putAuthToken(clientId: String, refreshToken: Token) {
		val first = authList.firstOrNull {
			it.equalsClientId(clientId)
		}
		if (first == null) {
			authList = authList.plus(
				UserAuth(
					user = this,
					clientId = clientId,
					token = refreshToken,
				)
			)
		} else {
			first.update(refreshToken)
		}
	}

	fun validRefreshToken(clientId: String, refreshToken: String): Boolean {
		val userAuth = authList.firstOrNull {
			it.equalsClientId(clientId)
		}
		userAuth ?: return false
		return userAuth.validRefreshToken(refreshToken)
	}

	fun updateName(name: String) {
		userName = UserName(name)
	}

	fun updatePassword(password: String, passwordEncoder: PasswordEncoder) {
		this.password = passwordEncoder.encrypt(password)
	}

	fun findAuthTokenByClientId(clientId: String): Token? {
		return authList.find { it.equalsClientId(clientId) }?.token
	}
}