package me.kjs.apimanagement.applications.application.port.out

import me.kjs.apimanagement.applications.domain.Application

interface FindOneApplicationPort {
	fun findById(id: String): Application?
}