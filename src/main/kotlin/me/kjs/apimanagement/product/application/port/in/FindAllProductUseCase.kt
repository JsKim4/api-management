package me.kjs.apimanagement.product.application.port.`in`

interface FindAllProductUseCase {
	fun findAll(): ProductForm.Find.Response.All
}