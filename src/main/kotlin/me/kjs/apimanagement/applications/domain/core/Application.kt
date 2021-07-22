package me.kjs.apimanagement.applications.domain.core

import me.kjs.apimanagement.user.domain.core.User

class Application(
	val id: String,
	applicationName: ApplicationName,
	applicationDescription: ApplicationDescription,
	user: User
) {
	var applicationName: ApplicationName = applicationName
		private set
	var applicationDescription: ApplicationDescription = applicationDescription
		private set
	var user: User = user
		private set
	val name = applicationName.title
	val description = applicationDescription.content


	companion object {
		fun by(id: String, title: String, content: String, user: User): Application =
			Application(
				id,
				ApplicationName(title),
				ApplicationDescription(content),
				user
			)
	}
}
