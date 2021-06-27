package me.kjs.apimanagement.user.domain.vo

import java.time.LocalDateTime
import java.time.temporal.ChronoField

class Token(
	val value: String,
	val expiredDateTime: LocalDateTime
) {
	val expiredSeconds: Long
		get() = expiredDateTime.getLong(ChronoField.INSTANT_SECONDS) - LocalDateTime.now().getLong(ChronoField.INSTANT_SECONDS)
}
