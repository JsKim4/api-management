package me.kjs.apimanagement.applications.presenstation

import me.kjs.apimanagement.common.Response
import me.kjs.apimanagement.common.ResponseResult
import me.kjs.apimanagement.common.ResponseType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping("/applications")
class ApplicationRestController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createApplication(
		@RequestBody request: ApplicationForm.Create.Request
	): ApplicationForm.Create.Response {
		TODO()
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun getApplications(
		@RequestHeader(value = "ResponseType", defaultValue = "SLICE", required = false) responseType: ResponseType,
		@RequestParam(value = "page", defaultValue = "0", required = true) @Min(1) page: Int,
		@RequestParam(value = "contentCount", defaultValue = "30", required = false) @Max(100) @Min(1) contentCount: Int,
	): ResponseResult<ApplicationForm.Find.Response.Simple> {
		TODO()
	}

	@GetMapping("/{applicationId}")
	@ResponseStatus(HttpStatus.OK)
	fun getApplication(
		@PathVariable applicationId: String
	): ApplicationForm.Find.Response.One {
		TODO()
	}

	@DeleteMapping("/{applicationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteApplication(
		@PathVariable applicationId: String,
	) {
		TODO()
	}

	@PatchMapping("/{applicationId}/secret-key")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun refreshSecretKey(
		@PathVariable applicationId: String
	) {
		TODO()
	}

}