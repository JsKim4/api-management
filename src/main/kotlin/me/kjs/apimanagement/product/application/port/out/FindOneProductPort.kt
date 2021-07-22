package me.kjs.apimanagement.product.application.port.out

import me.kjs.apimanagement.product.domain.Product
import me.kjs.apimanagement.product.domain.ProductCode

interface FindOneProductPort {
	fun findByCode(productCode: ProductCode): Product

}
