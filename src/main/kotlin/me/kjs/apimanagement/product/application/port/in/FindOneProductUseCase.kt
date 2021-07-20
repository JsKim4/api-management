package me.kjs.apimanagement.product.application.port.`in`

import me.kjs.apimanagement.product.domain.ProductCode

interface FindOneProductUseCase {
	fun findOne(productCode: ProductCode) : ProductForm.Find.Response.Simple
}