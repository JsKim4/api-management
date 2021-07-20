package me.kjs.apimanagement.user.application.port.out

interface FindEmailPort {
	fun existEmail(email: String): Boolean
}