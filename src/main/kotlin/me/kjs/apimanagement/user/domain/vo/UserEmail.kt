package me.kjs.apimanagement.user.domain.vo

import me.kjs.apimanagement.common.EMAIL_REG_EX

class UserEmail(val email: String) {
	init {
		if (!email.matches(EMAIL_REG_EX)) {
			throw RuntimeException("이메일 형식이 맞지 않습니다.")
		}
	}
}