package me.kjs.apimanagement.applications.application.port.`in`

import me.kjs.apimanagement.product.domain.ProductCode


interface UnUseProductAtApplicationUseCase {
	fun unUseProductAtApplication(productCode: ProductCode, applicationId: String)
}
