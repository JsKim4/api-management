package me.kjs.apimanagement.product.application.port.out

import me.kjs.apimanagement.product.domain.Product

interface FindAllProductPort {
	fun findAll(): List<Product>

}
