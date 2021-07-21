package me.kjs.apimanagement.applications.application.port.`in`


interface UseProductAtApplicationUseCase {
	fun useProductAtApplication(request: ApplicationProductForm.Create.Request): ApplicationProductForm.Create.Response?

}
