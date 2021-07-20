package me.kjs.apimanagement.applications.application.port.`in`

interface CreateApplicationUseCase {
	fun createApplication(request: ApplicationForm.Create.Request): ApplicationForm.Create.Response

}
