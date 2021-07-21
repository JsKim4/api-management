package me.kjs.apimanagement.applications.adapter.out.presenstation

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationProductForm
import me.kjs.apimanagement.applications.application.port.`in`.UnUseProductAtApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.UseProductAtApplicationUseCase
import me.kjs.apimanagement.product.adapter.out.presentation.ProductCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/applications/{applicationId}/products/{productCode}")
class ApplicationProductRestController(
	private val useProductAtApplicationUseCase: UseProductAtApplicationUseCase,
	private val unUseProductAtApplicationUseCase: UnUseProductAtApplicationUseCase,
) {

	@PutMapping
	fun createApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productCode: ProductCode,
		@RequestBody request: ApplicationProductPresentation.Create.Request
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

	fun me.kjs.apimanagement.applications.domain.ProcessStatus.toPresentation() = when (this) {
		me.kjs.apimanagement.applications.domain.ProcessStatus.ACCEPTED -> ApplicationProductPresentation.ProcessStatus.ACCEPTED
		me.kjs.apimanagement.applications.domain.ProcessStatus.REJECTED -> ApplicationProductPresentation.ProcessStatus.REJECTED
		me.kjs.apimanagement.applications.domain.ProcessStatus.REQUESTED -> ApplicationProductPresentation.ProcessStatus.REQUESTED
	}

	fun me.kjs.apimanagement.product.domain.ProductCode.toPresentation() = when (this) {
		me.kjs.apimanagement.product.domain.ProductCode.LOTTO -> ProductCode.LOTTO
	}


	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteApplicationProduct(
		@PathVariable applicationId: String,
		@PathVariable productCode: ProductCode,
	) {
		unUseProductAtApplicationUseCase.unUseProductAtApplication(productCode.toDomainCode(), applicationId)
	}


}