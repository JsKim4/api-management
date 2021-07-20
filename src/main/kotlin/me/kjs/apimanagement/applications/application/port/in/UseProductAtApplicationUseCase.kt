package me.kjs.apimanagement.applications.application.port.`in`


interface UseProductAtApplicationUseCase {
	fun useProductAtApplication(toFormWith: ApplicationProductForm.Create.Request): ApplicationProductForm.Create.Response

}
