package me.kjs.apimanagement.applications.application.port.out

import me.kjs.apimanagement.applications.domain.core.Application

interface FindApplicationPort {
	fun findApplication(id: String): Application?
}
