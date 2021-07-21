package me.kjs.apimanagement.applications.domain

import me.kjs.apimanagement.product.domain.ProductCode

class ApplicationProduct(
	val status: ProcessStatus,
	val productCode: ProductCode,
	val cause: String
) {
}
