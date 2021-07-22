package me.kjs.apimanagement.applications.domain.product

import me.kjs.apimanagement.applications.domain.core.ProcessStatus
import me.kjs.apimanagement.product.domain.ProductCode

class ApplicationProduct(
	val status: ProcessStatus,
	val productCode: ProductCode,
	val cause: String
) {
	companion object {
		fun by(productCode: ProductCode, cause: String) =
			ApplicationProduct(ProcessStatus.REQUESTED, productCode, cause)
	}
}
