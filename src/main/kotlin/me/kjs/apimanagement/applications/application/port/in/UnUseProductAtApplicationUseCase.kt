package me.kjs.apimanagement.applications.application.port.`in`

import me.kjs.apimanagement.product.adapter.out.presentation.ProductCode

interface UnUseProductAtApplicationUseCase {
	fun unUseProductAtApplication(productCode: ProductCode, applicationId: String)
}
