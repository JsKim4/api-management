package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.domain.vo.Token
import me.kjs.apimanagement.user.domain.vo.UserEmail
import me.kjs.apimanagement.user.domain.vo.UserName
import me.kjs.apimanagement.user.domain.vo.UserPassword

class User(
	val id: String,
	private var userName: UserName,
	private var userEmail: UserEmail,
	private var userPassword: UserPassword,
) {
	private var authList: List<UserAuth> = ArrayList()

	val name: String
		get() = userName.name
	val email: String
		get() = userEmail.email

	companion object {
		fun by(id: String, name: String, email: String, password: String, passwordEncoder: PasswordEncoder): User =
			User(id, UserName(name), UserEmail(email), UserPassword(password, passwordEncoder))
	}

	fun isEqualPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
		return userPassword.match(password, passwordEncoder)
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

	fun deleteAuthToken(clientId: String) {
		authList = authList.filterNot { it.equalsClientId(clientId) }
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
		this.userPassword = UserPassword(password, passwordEncoder)
	}

	fun findAuthTokenByClientId(clientId: String): Token? {
		return authList.find { it.equalsClientId(clientId) }?.token
	}
}