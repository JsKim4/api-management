package me.kjs.apimanagement.common.service

import me.kjs.apimanagement.common.port.out.IdGeneratePort
import org.springframework.stereotype.Service
import java.util.*

@Service
class IdGenerateService : IdGeneratePort {
	override fun generateId(): String {
		return UUID.randomUUID().toString()
	}
}