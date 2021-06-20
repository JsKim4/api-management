package me.kjs.apimanagement.applications.presenstation

import me.kjs.apimanagement.product.presentation.ProductCode

class ApplicationProductForm {
	class Create {
		data class Request(
			val cause: String
		)

		data class Response(
			val applicationId: String,
			val productCode: ProductCode,
			val processStatus: ProcessStatus,
		)

	}

	enum class ProcessStatus {
		REQUESTED, PROCESSING, ACCEPTED, REJECTED
	}

}
