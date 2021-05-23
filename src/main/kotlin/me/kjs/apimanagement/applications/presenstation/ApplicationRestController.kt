package me.kjs.apimanagement.applications.presenstation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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

	@PatchMapping("/{applicationId}/refresh-key")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun refreshKey(
		@PathVariable applicationId: String
	) {
		TODO()
	}

}