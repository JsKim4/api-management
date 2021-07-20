package me.kjs.apimanagement.product.application.port.`in`

interface PutProductUseCase {
	fun putProduct(request: ProductForm.Put.Request)
}