package me.kjs.apimanagement.product.adapter.out.presentation

import me.kjs.apimanagement.product.domain.ProductCode

enum class ProductCode {
	LOTTO;

	fun toDomainCode(): ProductCode {
		return when (this) {
			LOTTO -> ProductCode.LOTTO
		}
	}
}
