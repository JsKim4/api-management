package me.kjs.apimanagement.user.application.port.out

import me.kjs.apimanagement.user.domain.core.User

interface RecordUserPort {
	fun recordUser(user: User)
}