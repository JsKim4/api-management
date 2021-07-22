package me.kjs.apimanagement.applications.application.service

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationForm
import me.kjs.apimanagement.applications.application.port.`in`.CreateApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.DeleteApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.SecretKeyRefreshUseCase
import me.kjs.apimanagement.applications.application.port.out.*
import me.kjs.apimanagement.applications.domain.core.Application
import me.kjs.apimanagement.common.port.out.IdGeneratePort
import me.kjs.apimanagement.user.application.port.out.FindUserPort
import org.springframework.stereotype.Service

@Service
class ApplicationService(
	private val findUserPort: FindUserPort,
	private val findApplicationPort: FindApplicationPort,
	private val idGeneratePort: IdGeneratePort,
	private val clientIdGeneratePort: ClientIdGeneratePort,
	private val secretKeyGeneratePort: SecretKeyGeneratePort,
	private val recordApplicationPort: RecordApplicationPort,
	private val deleteApplicationPort: DeleteApplicationPort
) : DeleteApplicationUseCase, CreateApplicationUseCase, SecretKeyRefreshUseCase {

	override fun createApplication(request: ApplicationForm.Create.Request): ApplicationForm.Create.Response {
		val user = findUserPort.findUser(request.userId) ?: throw TODO()
		val clientId = clientIdGeneratePort.generateClientId()
		val secretKey = secretKeyGeneratePort.generateSecretKey()
		val application: Application =
			Application.by(idGeneratePort.generateId(), request.title, request.content, clientId, secretKey, user)
		recordApplicationPort.recordApplication(application)
		return application.toForm()
	}

	fun Application.toForm() = ApplicationForm.Create.Response(
		id, name, description, clientId, secretKey
	)

	override fun deleteById(applicationId: String, userId: String) {
		val application = findApplicationPort.findApplication(applicationId) ?: throw TODO()
		application.isOwner(userId)
		deleteApplicationPort.deleteApplication(application)
	}

	override fun refreshKeyById(applicationId: String, userId: String) {
		val application = findApplicationPort.findApplication(applicationId) ?: throw TODO()
		application.refreshSecretKey(secretKeyGeneratePort.generateSecretKey())
	}
}