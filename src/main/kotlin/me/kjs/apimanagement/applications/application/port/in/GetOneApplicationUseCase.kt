package me.kjs.apimanagement.applications.application.port.`in`

interface GetOneApplicationUseCase {
	fun getOne(applicationId: String): ApplicationForm.Find.Response.One

}
