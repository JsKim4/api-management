package me.kjs.apimanagement.applications.application.port.`in`

import me.kjs.apimanagement.applications.domain.ProcessStatus
import me.kjs.apimanagement.product.domain.ProductCode

interface ApplicationProductForm {
	interface Create {
		data class Request(
			val applicationId: String,
			val productCode: ProductCode,
			val cause: String
		)

		data class Response(
			val applicationId: String,
			val productCode: ProductCode,
			val processStatus: ProcessStatus,
			val cause: String
		)
	}
}