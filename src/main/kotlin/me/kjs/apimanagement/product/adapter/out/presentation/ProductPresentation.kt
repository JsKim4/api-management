package me.kjs.apimanagement.product.adapter.out.presentation

import me.kjs.apimanagement.product.application.port.`in`.ProductForm

interface ProductPresentation {
	interface Put {
		data class Request(
			val title: String,
			val content: String
		) {
			fun toFormWith(productCode: ProductCode) =
				ProductForm.Put.Request(productCode.toDomainCode(), title, content)
		}

		data class Response(
			val code: ProductCode,
			val title: String,
			val content: String
		)

	}

	interface Find {
		interface Response {
			data class All(
				val contents: List<Simple>
			)

			data class Simple(
				val code: ProductCode,
				val title: String,
				val content: String
			)

		}

	}

}
