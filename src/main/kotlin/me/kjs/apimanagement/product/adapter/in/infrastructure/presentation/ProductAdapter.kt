package me.kjs.apimanagement.product.adapter.`in`.infrastructure.presentation

import me.kjs.apimanagement.product.application.port.out.FindAllProductPort
import me.kjs.apimanagement.product.application.port.out.FindOneProductPort
import me.kjs.apimanagement.product.application.port.out.PutProductPort
import me.kjs.apimanagement.product.domain.IProduct
import me.kjs.apimanagement.product.domain.ProductCode
import org.springframework.stereotype.Repository

@Repository
class ProductAdapter : FindAllProductPort, FindOneProductPort, PutProductPort {

	override fun findAll(): List<IProduct> {
		TODO("Not yet implemented")
	}

	override fun findByCode(productCode: ProductCode): IProduct {
		TODO("Not yet implemented")
	}

	override fun putProduct(product: IProduct) {
		TODO("Not yet implemented")
	}
}