package me.kjs.apimanagement.applications.presenstation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/{applicationId}/product/{productId}")
class ApplicationProductRestController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productId: String,
		@RequestBody request: ApplicationProductForm.Create.Request
	): ApplicationProductForm.Create.Response {
		TODO()
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productId: String,
	) {
		TODO()
	}


}