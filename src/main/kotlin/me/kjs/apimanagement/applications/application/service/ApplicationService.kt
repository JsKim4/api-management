package me.kjs.apimanagement.applications.application.service

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationForm
import me.kjs.apimanagement.applications.application.port.`in`.CreateApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.DeleteApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.SecretKeyRefreshUseCase
import org.springframework.stereotype.Service

@Service
class ApplicationService: DeleteApplicationUseCase,CreateApplicationUseCase,SecretKeyRefreshUseCase {
	override fun createApplication(request: ApplicationForm.Create.Request): ApplicationForm.Create.Response {
		TODO()
	}

	override fun deleteById(applicationId: String) {
		TODO("Not yet implemented")
	}

	override fun refreshKeyById(applicationId: String) {
		TODO("Not yet implemented")
	}
}