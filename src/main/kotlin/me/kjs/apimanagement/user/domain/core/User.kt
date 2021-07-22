package me.kjs.apimanagement.user.domain.core

import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import me.kjs.apimanagement.user.domain.vo.UserEmail
import me.kjs.apimanagement.user.domain.vo.UserName
import me.kjs.apimanagement.user.domain.vo.UserPassword

class User(
	val id: String,
	private var userName: UserName,
	private var userEmail: UserEmail,
	private var userPassword: UserPassword,
) {
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


	fun updateName(name: String) {
		userName = UserName(name)
	}

	fun updatePassword(password: String, passwordEncoder: PasswordEncoder) {
		this.userPassword = UserPassword(password, passwordEncoder)
	}
}