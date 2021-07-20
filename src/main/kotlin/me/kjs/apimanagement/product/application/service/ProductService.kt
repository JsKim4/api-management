package me.kjs.apimanagement.product.application.service

import me.kjs.apimanagement.product.application.port.`in`.FindAllProductUseCase
import me.kjs.apimanagement.product.application.port.`in`.FindOneProductUseCase
import me.kjs.apimanagement.product.application.port.`in`.ProductForm
import me.kjs.apimanagement.product.application.port.`in`.PutProductUseCase
import me.kjs.apimanagement.product.application.port.out.FindAllProductPort
import me.kjs.apimanagement.product.application.port.out.FindOneProductPort
import me.kjs.apimanagement.product.application.port.out.PutProductPort
import me.kjs.apimanagement.product.domain.IProduct
import me.kjs.apimanagement.product.domain.Product
import me.kjs.apimanagement.product.domain.ProductCode
import org.springframework.stereotype.Service

@Service
class ProductService(
	private val findAllProductPort: FindAllProductPort,
	private val findOneProductPort: FindOneProductPort,
	private val putProductPort: PutProductPort,
) : FindAllProductUseCase, FindOneProductUseCase, PutProductUseCase {
	override fun findAll(): ProductForm.Find.Response.All {
		return ProductForm.Find.Response.All(findAllProductPort.findAll().map { it.toSimpleForm() })
	}

	override fun findOne(productCode: ProductCode): ProductForm.Find.Response.Simple {
		return findOneProductPort.findByCode(productCode).toSimpleForm()
	}

	fun IProduct.toSimpleForm() = ProductForm.Find.Response.Simple(productCode, title, content)

	override fun putProduct(request: ProductForm.Put.Request) {
		findOneProductPort.findByCode(request.productCode)
		putProductPort.putProduct(request.toDomain())
	}

	fun ProductForm.Put.Request.toDomain(): Product = Product(productCode, title, content)
}