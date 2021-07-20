package me.kjs.apimanagement.user.domain.vo

import me.kjs.apimanagement.user.application.port.out.PasswordEncoder

class UserPassword private constructor(val password: String) {
	constructor(password: String, passwordEncoder: PasswordEncoder) : this(passwordEncoder.encrypt(password))

	fun match(password: String, passwordEncoder: PasswordEncoder): Boolean {
		return passwordEncoder.match(this.password, password)
	}

}
