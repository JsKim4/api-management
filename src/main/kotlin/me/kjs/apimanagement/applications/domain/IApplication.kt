package me.kjs.apimanagement.applications.domain

import me.kjs.apimanagement.user.domain.User

interface IApplication {
	val id: String
	val name: ApplicationName
	val description: ApplicationDescription
	val user: User
}