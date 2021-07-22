package me.kjs.apimanagement.applications.application.service

import me.kjs.apimanagement.applications.application.port.`in`.ApplicationProductForm
import me.kjs.apimanagement.applications.application.port.`in`.UnUseProductAtApplicationUseCase
import me.kjs.apimanagement.applications.application.port.`in`.UseProductAtApplicationUseCase
import me.kjs.apimanagement.applications.application.port.out.FindOneApplicationPort
import me.kjs.apimanagement.applications.application.port.out.RecordApplicationPort
import me.kjs.apimanagement.applications.domain.product.ApplicationProduct
import me.kjs.apimanagement.product.domain.ProductCode
import org.springframework.stereotype.Service

@Service
class ProductAtApplicationService(
	private val findOneApplicationPort: FindOneApplicationPort,
	private val recordApplicationPort: RecordApplicationPort
) : UnUseProductAtApplicationUseCase, UseProductAtApplicationUseCase {

	override fun unUseProductAtApplication(productCode: ProductCode, applicationId: String) {
		val application = findOneApplicationPort.findById(applicationId) ?: TODO()
		application.unUseProduct(productCode)
		recordApplicationPort.recordApplication(application)
	}

	override fun useProductAtApplication(request: ApplicationProductForm.Create.Request): ApplicationProductForm.Create.Response? {
		val application = findOneApplicationPort.findById(request.applicationId) ?: TODO()
		val result = application.useProduct(request.productCode, request.cause)
		recordApplicationPort.recordApplication(application)
		return result.toFormWithApplicationId(application.id)
	}

	fun ApplicationProduct?.toFormWithApplicationId(applicationId: String) =
		this?.let {
			ApplicationProductForm.Create.Response(
				applicationId, productCode, status, cause
			)
		}

}