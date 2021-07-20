package me.kjs.apimanagement.product.domain

class Product(
	productCode: ProductCode,
	title: String,
	content: String
) : IProduct {
	override var productCode: ProductCode = productCode
		private set
	override var title: String = title
		private set
	override var content: String = content
		private set
}
