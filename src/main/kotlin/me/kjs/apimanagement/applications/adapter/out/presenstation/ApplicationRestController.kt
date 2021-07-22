package me.kjs.apimanagement.applications.adapter.out.presenstation

import me.kjs.apimanagement.applications.application.port.`in`.*
import me.kjs.apimanagement.common.ResponseResult
import me.kjs.apimanagement.common.ResponseType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping("/users/{userId}/applications")
class ApplicationRestController(
	private val createApplicationUseCase: CreateApplicationUseCase,
	private val getOneApplicationUseCase: GetOneApplicationUseCase,
	private val findOnPageApplicationUseCase: FindOnPageApplicationUseCase,
	private val deleteApplicationUseCase: DeleteApplicationUseCase,
	private val secretKeyRefreshUseCase: SecretKeyRefreshUseCase,
) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createApplication(
		@RequestBody request: ApplicationPresentation.Create.Request,
		@PathVariable userId: String
	): ApplicationPresentation.Create.Response {
		return createApplicationUseCase.createApplication(request.toFormWith(userId)).toPresentation()
	}

	fun ApplicationForm.Create.Response.toPresentation() =
		ApplicationPresentation.Create.Response(title, content, clientId, secretKey)


	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun getApplications(
		@RequestHeader(value = "ResponseType", defaultValue = "SLICE", required = false) responseType: ResponseType,
		@RequestParam(value = "page", defaultValue = "0", required = true) @Min(1) page: Int,
		@RequestParam(value = "contentCount", defaultValue = "30", required = false) @Max(100) @Min(1) contentCount: Int,
		@PathVariable userId: String,
	): ResponseResult<ApplicationPresentation.Find.Response.Simple> {
		return when (responseType) {
			ResponseType.SLICE -> findOnPageApplicationUseCase.findSlice(page, contentCount)
			ResponseType.PAGE -> findOnPageApplicationUseCase.findPage(page, contentCount)
		}.listTo { it.toPresentation() }
	}

	fun ApplicationForm.Find.Response.Simple.toPresentation() =
		ApplicationPresentation.Find.Response.Simple(id, title)

	@GetMapping("/{applicationId}")
	@ResponseStatus(HttpStatus.OK)
	fun getApplication(
		@PathVariable applicationId: String,
		@PathVariable userId: String
	): ApplicationPresentation.Find.Response.One {
		return getOneApplicationUseCase.getOne(applicationId).toPresentation()
	}

	fun ApplicationForm.Find.Response.One.toPresentation() =
		ApplicationPresentation.Find.Response.One(id, title, content, clientId, secretKey)

	@DeleteMapping("/{applicationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteApplication(
		@PathVariable applicationId: String,
		@PathVariable userId: String,
	) {
		deleteApplicationUseCase.deleteById(applicationId, userId)
	}

	@PatchMapping("/{applicationId}/secret-key")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun refreshSecretKey(
		@PathVariable applicationId: String,
		@PathVariable userId: String
	) {
		secretKeyRefreshUseCase.refreshKeyById(applicationId, userId)
	}

}