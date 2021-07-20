package me.kjs.apimanagement.applications.application.service

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationProductForm
import me.kjs.apimanagement.applications.application.port.`in`.UnUseProductAtApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.UseProductAtApplicationUseCase
import me.kjs.apimanagement.product.adapter.out.presentation.ProductCode
import org.springframework.stereotype.Service

@Service
class ProductAtApplicationService : UnUseProductAtApplicationUseCase, UseProductAtApplicationUseCase {

	override fun unUseProductAtApplication(productCode: ProductCode, applicationId: String) {
		TODO("Not yet implemented")
	}

	override fun useProductAtApplication(toFormWith: ApplicationProductForm.Create.Request): ApplicationProductForm.Create.Response {
		TODO("Not yet implemented")
	}
}