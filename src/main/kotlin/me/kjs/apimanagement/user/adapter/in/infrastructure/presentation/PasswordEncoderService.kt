package me.kjs.apimanagement.user.adapter.`in`.infrastructure.presentation

import me.kjs.apimanagement.user.application.port.out.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordEncoderService : PasswordEncoder {
	override fun match(encryptedPassword: String, lawPassword: String): Boolean {
		return encryptedPassword == lawPassword
	}

	override fun encrypt(lawPassword: String): String {
		return lawPassword
	}
}
