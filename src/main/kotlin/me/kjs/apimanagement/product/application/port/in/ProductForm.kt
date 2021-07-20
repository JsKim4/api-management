package me.kjs.apimanagement.product.application.port.`in`

import me.kjs.apimanagement.product.domain.ProductCode

interface ProductForm {
	interface Put {
		data class Request(
			val productCode: ProductCode,
			val title: String,
			val content: String
		)
	}

	interface Find {
		interface Response {
			data class Simple(
				val productCode: ProductCode,
				val title: String,
				val content: String
			)

			data class All(
				val contents: List<Simple>
			)
		}
	}
}