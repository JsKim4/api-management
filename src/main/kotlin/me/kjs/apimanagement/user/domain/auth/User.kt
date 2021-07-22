package me.kjs.apimanagement.user.domain.auth

import me.kjs.apimanagement.user.domain.UserAuth
import me.kjs.apimanagement.user.domain.vo.Token

class User(
	val id: String,
	private var authList: List<UserAuth> = ArrayList()
) {
	fun putAuthToken(clientId: String, refreshToken: Token) {
		val first = authList.firstOrNull {
			it.equalsClientId(clientId)
		}
		if (first == null) {
			authList = authList.plus(
				UserAuth(
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

	fun findAuthTokenByClientId(clientId: String): Token? {
		return authList.find { it.equalsClientId(clientId) }?.token
	}
}