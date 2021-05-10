package me.kjs.apimanagement.user.domain

import me.kjs.apimanagement.common.PasswordEncoder
import me.kjs.apimanagement.common.generateId
import me.kjs.apimanagement.user.domain.vo.UserEmail
import me.kjs.apimanagement.user.domain.vo.UserName
import java.time.LocalDateTime

class User(
	name: String,
	email: String,
	password: String,
) {
	val id: String = generateId()
	private var userName: UserName = UserName(name)
	private var userEmail: UserEmail = UserEmail(email)
	private var password: String = password
	private var authList: List<UserAuth> = ArrayList()

	val name: String
		get() = userName.name
	val email: String
		get() = userEmail.email

	fun isEqualPassword(password: String, passwordEncoder: PasswordEncoder): Boolean {
		return passwordEncoder.match(this.password, password)
	}

	fun putAuthToken(clientId: String, refreshToken: String, expiredDateTime: LocalDateTime) {
		val first = authList.firstOrNull {
			it.equalsClientId(clientId)
		}
		if (first == null) {
			authList = authList.plus(
				UserAuth(
					user = this,
					clientId = clientId,
					refreshToken = refreshToken,
					expiredDateTime = expiredDateTime
				)
			)
		} else {
			first.update(refreshToken, expiredDateTime)
		}
	}

	fun validRefreshToken(clientId: String, refreshToken: String): Boolean {
		val userAuth = authList.firstOrNull {
			it.equalsClientId(clientId)
		}
		userAuth ?: return false
		return userAuth.validRefreshToken(refreshToken)
	}
}