package me.kjs.apimanagement.product.application.port.out

import me.kjs.apimanagement.product.domain.IProduct

interface PutProductPort {
	fun putProduct(product: IProduct)

}
