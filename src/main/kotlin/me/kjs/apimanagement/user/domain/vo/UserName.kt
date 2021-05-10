package me.kjs.apimanagement.user.domain.vo

import me.kjs.apimanagement.common.NAME_MAX_LENGTH
import me.kjs.apimanagement.common.NAME_MIN_LENGTH

class UserName(val name: String) {
	init {
		val length = name.length
		if (length < NAME_MIN_LENGTH || length > NAME_MAX_LENGTH) {
			throw RuntimeException("이름이 너무 짧거나 깁니다.")
		}
	}
}