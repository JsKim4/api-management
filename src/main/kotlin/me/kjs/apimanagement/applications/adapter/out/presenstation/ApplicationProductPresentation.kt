package me.kjs.apimanagement.applications.adapter.out.presenstation

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationProductForm
import me.kjs.apimanagement.product.adapter.out.presentation.ProductCode

interface ApplicationProductPresentation {
	interface Create {
		data class Request(
			val cause: String
		) {
			fun toFormWith(applicationId: String, productCode: ProductCode) =
				ApplicationProductForm.Create.Request(
					applicationId, productCode.toDomainCode(), cause
				)
		}

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
