package com.ducnguyen2102.baseclean.domain.usecase.product

import com.ducnguyen2102.baseclean.domain.base.UseCase
import com.ducnguyen2102.baseclean.domain.model.Product
import com.ducnguyen2102.baseclean.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : UseCase<GetProductUseCase.Param, Product>() {
    override suspend fun execute(param: Param): Product {
        return productRepository.getProduct(param.id)
    }

    data class Param(val id: Int) : UseCase.Param()
}