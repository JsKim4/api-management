package me.kjs.apimanagement.common

interface PasswordEncoder {
	fun match(encryptedPassword: String, lawPassword: String): Boolean {
		return encryptedPassword == lawPassword
	}

	fun encrypt(lawPassword: String): String {
		return lawPassword
	}
}
