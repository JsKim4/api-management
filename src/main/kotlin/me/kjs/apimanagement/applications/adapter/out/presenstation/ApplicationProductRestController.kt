package me.kjs.apimanagement.applications.adapter.out.presenstation

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationProductForm
import me.kjs.apimanagement.applications.application.port.`in`.UnUseProductAtApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.UseProductAtApplicationUseCase
import me.kjs.apimanagement.applications.domain.core.ProcessStatus
import me.kjs.apimanagement.product.adapter.out.presentation.ProductCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users/{userId}/applications/{applicationId}/products/{productCode}")
class ApplicationProductRestController(
	private val useProductAtApplicationUseCase: UseProductAtApplicationUseCase,
	private val unUseProductAtApplicationUseCase: UnUseProductAtApplicationUseCase,
) {

	@PutMapping
	fun createApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productCode: ProductCode,
		@RequestBody request: ApplicationProductPresentation.Create.Request,
		@PathVariable userId: String
	): ResponseEntity<ApplicationProductPresentation.Create.Response?> {
		val result = useProductAtApplicationUseCase.useProductAtApplication(request.toFormWith(applicationId, productCode))
		return result?.let {
			ResponseEntity.status(HttpStatus.CREATED).body(it.toPresentation())
		} ?: ResponseEntity.noContent().build()
	}

	private fun ApplicationProductForm.Create.Response.toPresentation() =
		ApplicationProductPresentation.Create.Response(
			applicationId, productCode.toPresentation(), processStatus.toPresentation()
		)

	fun ProcessStatus.toPresentation() = when (this) {
		ProcessStatus.ACCEPTED -> ApplicationProductPresentation.ProcessStatus.ACCEPTED
		ProcessStatus.CANCELED -> ApplicationProductPresentation.ProcessStatus.CANCELED
		ProcessStatus.REQUESTED -> ApplicationProductPresentation.ProcessStatus.REQUESTED
	}

	fun me.kjs.apimanagement.product.domain.ProductCode.toPresentation() = when (this) {
		me.kjs.apimanagement.product.domain.ProductCode.LOTTO -> ProductCode.LOTTO
	}


	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productCode: ProductCode,
		@PathVariable userId: String,
	) {
		unUseProductAtApplicationUseCase.unUseProductAtApplication(productCode.toDomainCode(), applicationId)
	}


}