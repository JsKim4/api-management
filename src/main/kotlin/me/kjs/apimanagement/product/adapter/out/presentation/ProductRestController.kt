package me.kjs.apimanagement.product.adapter.out.presentation

import me.kjs.apimanagement.product.application.port.`in`.FindAllProductUseCase
import me.kjs.apimanagement.product.application.port.`in`.FindOneProductUseCase
import me.kjs.apimanagement.product.application.port.`in`.ProductForm
import me.kjs.apimanagement.product.application.port.`in`.PutProductUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProductRestController(
	private val finAllProductUseCase: FindAllProductUseCase,
	private val findOneProductUseCase: FindOneProductUseCase,
	private val putProductUseCase: PutProductUseCase,
) {


	@PutMapping("/{productCode}")
	fun updateProject(
		@RequestBody request: ProductPresentation.Put.Request,
		@PathVariable productCode: ProductCode
	): ResponseEntity<Unit> {
		putProductUseCase.putProduct(request.toFormWith(productCode))

		return ResponseEntity.noContent().build()
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun queryProducts(

	): ProductPresentation.Find.Response.All {
		return finAllProductUseCase.findAll().toPresentation()
	}

	fun me.kjs.apimanagement.product.domain.ProductCode.toPresentation() = when (this) {
		me.kjs.apimanagement.product.domain.ProductCode.LOTTO -> ProductCode.LOTTO
	}

	fun ProductForm.Find.Response.Simple.toPresentation() = ProductPresentation.Find.Response.Simple(
		productCode.toPresentation(), title, content
	)

	fun ProductForm.Find.Response.All.toPresentation() = ProductPresentation.Find.Response.All(
		contents.map { it.toPresentation() })


	@GetMapping("/{productCode}")
	@ResponseStatus(HttpStatus.OK)
	fun queryProduct(
		@PathVariable productCode: ProductCode
	): ProductPresentation.Find.Response.Simple {
		return findOneProductUseCase.findOne(productCode.toDomainCode()).toPresentation()
	}


}