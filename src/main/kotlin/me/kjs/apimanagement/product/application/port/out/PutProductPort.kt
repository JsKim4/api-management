package me.kjs.apimanagement.product.application.port.out

import me.kjs.apimanagement.product.domain.Product

interface PutProductPort {
	fun putProduct(product: Product)

}
