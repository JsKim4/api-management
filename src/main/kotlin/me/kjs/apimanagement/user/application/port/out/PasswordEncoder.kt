package me.kjs.apimanagement.user.application.port.out

interface PasswordEncoder {
	fun match(encryptedPassword: String, lawPassword: String): Boolean
	fun encrypt(lawPassword: String): String
}