package me.kjs.apimanagement.applications.domain.product

import me.kjs.apimanagement.product.domain.ProductCode

class Application(
	val id: String,
	var applicationProducts: ApplicationProducts
) {
	fun useProduct(productCode: ProductCode, cause: String): ApplicationProduct? {
		val applicationProduct = ApplicationProduct.by(productCode, cause)
		if(applicationProducts.hasActivation(applicationProduct)){
			return null
		}
		applicationProducts = applicationProducts.useProduct(applicationProduct)
		return applicationProduct
	}

	fun unUseProduct(productCode: ProductCode) {
		applicationProducts = applicationProducts.unUseProduct(productCode)
	}
}