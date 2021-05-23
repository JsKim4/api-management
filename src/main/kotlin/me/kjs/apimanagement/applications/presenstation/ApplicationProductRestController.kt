package me.kjs.apimanagement.applications.presenstation

import me.kjs.apimanagement.product.presentation.ProjectCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/{applicationId}/product/{productCode}")
class ApplicationProductRestController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productCode: ProjectCode,
		@RequestBody request: ApplicationProductForm.Create.Request
	): ApplicationProductForm.Create.Response {
		TODO()
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productCode: ProjectCode,
	) {
		TODO()
	}


}