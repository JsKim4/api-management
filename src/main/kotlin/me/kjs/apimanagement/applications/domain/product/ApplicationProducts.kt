package me.kjs.apimanagement.applications.domain.product

import me.kjs.apimanagement.applications.domain.core.ProcessStatus
import me.kjs.apimanagement.product.domain.ProductCode

class ApplicationProducts(val list: List<ApplicationProduct>) {

	init {
		if (list.distinctBy { it.productCode }.size != list.size) {
			throw IllegalArgumentException("프로덕트 코드 하나씩만 가질수 있습니다.")
		}
	}

	fun useProduct(applicationProduct: ApplicationProduct): ApplicationProducts {
		return ApplicationProducts(list.filterNot { it.productCode == applicationProduct.productCode } + applicationProduct)
	}

	fun unUseProduct(productCode: ProductCode): ApplicationProducts {
		return ApplicationProducts(list.filterNot { it.productCode == productCode })
	}

	fun hasActivation(applicationProduct: ApplicationProduct): Boolean {
		return list.any { it.productCode == applicationProduct.productCode && it.status != ProcessStatus.CANCELED }
	}
}
