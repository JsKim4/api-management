package me.kjs.apimanagement.applications.domain

import me.kjs.apimanagement.product.domain.ProductCode

class Application(
	val id: String
) {
	fun useProduct(productCode: ProductCode, cause: String): ApplicationProduct? {
		TODO("Not yet implemented")
	}

	fun getStatusByCode(productCode: ProductCode): ProcessStatus {
		TODO("Not yet implemented")
	}

	fun unUseProduct(productCode: ProductCode) {
		TODO("Not yet implemented")
	}

}
